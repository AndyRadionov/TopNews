package io.github.andyradionov.topnews.ui.common

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import dagger.android.support.AndroidSupportInjection
import io.github.andyradionov.topnews.R
import io.github.andyradionov.topnews.data.entities.Article
import io.github.andyradionov.topnews.ui.adapter.NewsAdapter
import kotlinx.android.synthetic.main.news_content_layout.*
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
abstract class BaseNewsFragment : MvpAppCompatFragment(), BaseNewsView {

    @Inject
    lateinit var newsAdapter: NewsAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.news_content_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSwipeRefresh()
        setUpRecycler()
    }

    override fun onResume() {
        super.onResume()
        if (newsAdapter.itemCount == 0) {
            loadNews()
        }
    }

    override fun showNews(articles: List<Article>) {
        setVisibility(container = true)
        newsAdapter.items = articles
    }

    override fun showError() {
        setVisibility(empty = true)
    }

    override fun showLoading() {
        setVisibility(container = false, loading = true)
    }

    protected abstract fun setUpSwipeRefresh()

    protected abstract fun loadNews()

    private fun setUpRecycler() {
        rv_news_container.adapter = newsAdapter

        val columnsNumber = resources.getInteger(R.integer.columns_number)
        val layoutManager = GridLayoutManager(activity, columnsNumber)

        rv_news_container.layoutManager = layoutManager
    }

    private fun setVisibility(container: Boolean = false,
                              loading: Boolean = false,
                              empty: Boolean = false) {

        swipe_container.isRefreshing = loading
        rv_news_container.visibility = getVisibility(container)
        tv_empty_view.visibility = getVisibility(empty)
    }

    private fun getVisibility(isVisible: Boolean) = if (isVisible) View.VISIBLE else View.INVISIBLE
}
