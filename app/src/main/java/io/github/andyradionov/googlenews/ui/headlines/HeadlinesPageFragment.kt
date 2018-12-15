package io.github.andyradionov.googlenews.ui.headlines

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.github.andyradionov.googlenews.R
import io.github.andyradionov.googlenews.data.entities.Article
import io.github.andyradionov.googlenews.ui.common.BaseFragment
import io.github.andyradionov.googlenews.ui.common.adapter.NewsAdapter
import io.github.andyradionov.googlenews.ui.common.views.BaseNewsView
import kotlinx.android.synthetic.main.content_layout.*
import javax.inject.Inject

private const val ARG_PAGE_HEADLINE = "page_headline"

class HeadlinesPageFragment : BaseFragment(), BaseNewsView {

    @Inject
    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: HeadlinesPresenter
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var pageHeadline: String

    @ProvidePresenter
    fun providePresenter(): HeadlinesPresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pageHeadline = it.getString(ARG_PAGE_HEADLINE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //todo contentLay
        return inflater.inflate(R.layout.content_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSwipeRefresh()
        setUpRecycler()
    }

    override fun onResume() {
        super.onResume()
        loadNews()
    }

    override fun showNews(articles: List<Article>) {
        setVisibility(container = true)
        newsAdapter.items = articles
    }

    override fun showError() {
        setVisibility(empty = true)
    }

    override fun showLoading() {
        setVisibility(loading = true)
    }

    private fun setUpSwipeRefresh() {
        swipe_container.setOnRefreshListener {
            presenter.fetchNewsForHeadline(pageHeadline)
        }
    }

    private fun setUpRecycler() {
        newsAdapter = NewsAdapter(onArticleClickListener)
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

    private fun loadNews() {
        showLoading()
        presenter.fetchNewsForHeadline(pageHeadline)
    }

    companion object {
        fun newInstance(pageHeadline: String) =
                HeadlinesPageFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PAGE_HEADLINE, pageHeadline)
                    }
                }
    }
}
