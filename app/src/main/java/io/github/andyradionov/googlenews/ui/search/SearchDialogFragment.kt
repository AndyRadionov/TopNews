package io.github.andyradionov.googlenews.ui.search

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import com.arellomobile.mvp.MvpAppCompatDialogFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.android.support.AndroidSupportInjection
import io.github.andyradionov.googlenews.R
import io.github.andyradionov.googlenews.data.entities.Article
import io.github.andyradionov.googlenews.ui.adapter.NewsAdapter
import io.github.andyradionov.googlenews.utils.EMPTY_STRING
import kotlinx.android.synthetic.main.news_content_layout.*
import kotlinx.android.synthetic.main.fragment_search_dialog.*
import javax.inject.Inject

class SearchDialogFragment : MvpAppCompatDialogFragment(), SearchNewsView {

    @Inject
    @InjectPresenter
    lateinit var presenter: SearchPresenter

    @Inject
    lateinit var newsAdapter: NewsAdapter
    private var query: String = EMPTY_STRING

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
        setUpSwipeRefresh()
        setUpRecycler()
        createMenu()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.SearchDialogAnimation;
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
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

    private fun createMenu() {

        toolbar.inflateMenu(R.menu.search)
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        toolbar.setNavigationOnClickListener {
            dismiss()
        }

        val searchAction = toolbar.menu.findItem(R.id.action_search)
        val searchView = searchAction.actionView as SearchView
        searchView.setIconifiedByDefault(false)
        searchView.imeOptions = EditorInfo.IME_FLAG_FORCE_ASCII
        val magImage = searchView
                .findViewById(android.support.v7.appcompat.R.id.search_mag_icon) as ImageView?
        magImage?.visibility = View.GONE
        magImage?.setImageDrawable(null)

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
            swipe_container.isRefreshing = false
            if (query != EMPTY_STRING) {
                presenter.searchNews(query)
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

        rv_news_container.visibility = if (container) View.VISIBLE else View.INVISIBLE
        swipe_container.isRefreshing = loading
        tv_empty_view.visibility = if (empty) View.VISIBLE else View.INVISIBLE
    }

    companion object {
        val TAG = SearchDialogFragment::class.java.simpleName
    }
}
