package io.github.andyradionov.topnews.ui.search

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.arellomobile.mvp.MvpAppCompatDialogFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.android.support.AndroidSupportInjection
import io.github.andyradionov.topnews.R
import io.github.andyradionov.topnews.data.entities.Article
import io.github.andyradionov.topnews.ui.adapter.NewsAdapter
import io.github.andyradionov.topnews.ui.common.BaseNewsView
import io.github.andyradionov.topnews.ui.common.`BaseNewsView$$State`
import io.github.andyradionov.topnews.utils.getVisibility
import kotlinx.android.synthetic.main.fragment_search_dialog.*
import kotlinx.android.synthetic.main.news_content_layout.*
import javax.inject.Inject

class SearchDialogFragment : MvpAppCompatDialogFragment(), BaseNewsView {

    @Inject
    @InjectPresenter
    lateinit var presenter: SearchPresenter

    @Inject
    lateinit var newsAdapter: NewsAdapter
    private lateinit var searchView: SearchView

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setUpSwipeRefresh()
        setUpRecycler()
        createMenu()
    }

    override fun onDestroyView() {
        if (dialog != null && retainInstance) {
            dialog.setDismissMessage(null)
        }
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.SearchDialogAnimation
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        val viewState = `BaseNewsView$$State`()
        viewState.attachView(this)
        presenter.setViewState(viewState)
        super.onAttach(context)
    }

    override fun showNews(articles: List<Article>) {
        setVisibility(container = true)
        newsAdapter.items = articles
        swipe_container.isRefreshing = false
    }

    override fun showError() {
        setVisibility(empty = true)
    }

    override fun showLoading() {
        setVisibility(container = false, loading = true)
    }

    private fun initViews() {
        tv_empty_view.setText(R.string.empty_view_search)
        setVisibility(empty = true)
    }

    private fun createMenu() {

        toolbar.inflateMenu(R.menu.search)
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        toolbar.setNavigationOnClickListener {
            dismiss()
        }

        val searchAction = toolbar.menu.findItem(R.id.action_search)
        searchView = searchAction.actionView as SearchView
        searchView.setIconifiedByDefault(false)
        val magImage = searchView
                .findViewById(android.support.v7.appcompat.R.id.search_mag_icon) as ImageView?
        magImage?.visibility = View.GONE
        magImage?.setImageDrawable(null)

        searchView.maxWidth = Integer.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                swipe_container.isRefreshing = true
                presenter.searchNews(query)
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun setUpSwipeRefresh() {
        swipe_container.setOnRefreshListener {
            if (searchView.query.isNotEmpty()) {
                presenter.searchNews(searchView.query.toString())
            }
        }
    }

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
        rv_news_container.visibility = rv_news_container.getVisibility(container)
        tv_empty_view.visibility = tv_empty_view.getVisibility(empty)
    }

    companion object {
        val TAG: String = SearchDialogFragment::class.java.simpleName
    }
}
