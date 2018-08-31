package andyradionov.github.io.googlenews.ui.news

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import andyradionov.github.io.googlenews.R
import andyradionov.github.io.googlenews.app.App
import andyradionov.github.io.googlenews.data.entities.Article
import andyradionov.github.io.googlenews.ui.details.WebViewActivity
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateActivity
import kotlinx.android.synthetic.main.activity_news.*
import javax.inject.Inject

class NewsActivity : MvpViewStateActivity<NewsContract.View, NewsContract.Presenter, NewsViewState>(),
        NewsContract.View,
        NewsAdapter.OnArticleClickListener {

    companion object {
        const val EMPTY_QUERY = ""
    }

    @Inject
    lateinit var mPresenter: NewsContract.Presenter
    private lateinit var mNewsAdapter: NewsAdapter
    private var mQuery: String = EMPTY_QUERY

    override fun createPresenter() = mPresenter

    override fun createViewState() = NewsViewState()

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        setUpRecycler()
    }

    override fun onResume() {
        super.onResume()
        mQuery = viewState.getQuery()
        if (mQuery.isEmpty()) {
            mPresenter.getTopNews()
        } else {
            invalidateOptionsMenu()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)

        val searchAction = menu.findItem(R.id.action_search)

        val searchView = searchAction.actionView as SearchView
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                showLoading()
                mQuery = query
                viewState.setQuery(query)
                mPresenter.searchNews(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        searchAction.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                searchView.imeOptions = EditorInfo.IME_FLAG_FORCE_ASCII
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                invalidateOptionsMenu()
                mPresenter.getTopNews()
                showLoading()
                return true
            }
        })

        if (!mQuery.isEmpty()) {
            searchAction.expandActionView()
            searchView.setQuery(mQuery, true)
            searchView.clearFocus()
        }

        return true
    }

    override fun showNews(articles: List<Article>) {
        viewState.setData(articles)
        setVisibility(container = true)
        mNewsAdapter.updateData(articles)
    }

    override fun showError() {
        viewState.setShowError()
        setVisibility(empty = true)
        mNewsAdapter.clearData();
    }

    override fun showLoading() {
        viewState.setShowLoading()
        setVisibility(loading = true)
        mNewsAdapter.clearData();
    }

    override fun onClick(articleUrl: String) {
        intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra(WebViewActivity.ARTICLE_URL_EXTRA, articleUrl)
        startActivity(intent)
    }

    override fun onNewViewStateInstance() { /*NOP*/ }

    private fun setUpRecycler() {
        mNewsAdapter = NewsAdapter(this)
        rv_news_container.adapter = mNewsAdapter

        val columnsNumber = resources.getInteger(R.integer.columns_number)
        val layoutManager = GridLayoutManager(this, columnsNumber)

        rv_news_container.layoutManager = layoutManager
    }

    private fun setVisibility(container: Boolean = false,
                              loading: Boolean = false,
                              empty: Boolean = false) {

        rv_news_container.visibility = if (container) View.VISIBLE else View.INVISIBLE
        pb_loading.visibility = if (loading) View.VISIBLE else View.INVISIBLE
        tv_empty_view.visibility = if (empty) View.VISIBLE else View.INVISIBLE
    }
}