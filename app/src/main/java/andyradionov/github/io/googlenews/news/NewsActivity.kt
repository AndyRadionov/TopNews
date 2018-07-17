package andyradionov.github.io.googlenews.news

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import andyradionov.github.io.googlenews.R
import andyradionov.github.io.googlenews.app.App
import andyradionov.github.io.googlenews.data.Article
import javax.inject.Inject

import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity(), NewsContract.View, NewsAdapter.OnArticleClickListener {

    companion object {
        const val EXTRA_QUERY = "extra_query"
        const val EMPTY_QUERY = ""
    }

    @Inject lateinit var mPresenter: NewsContract.Presenter
    private lateinit var mNewsAdapter: NewsAdapter
    private var mQuery: String = EMPTY_QUERY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        App.appComponent.inject(this)

        setUpRecycler()
        mPresenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun onResume() {
        super.onResume()
        if (mQuery.isEmpty()) {
            mPresenter.getTopNews()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        mQuery = savedInstanceState?.getString(EXTRA_QUERY) ?: EMPTY_QUERY
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString(EXTRA_QUERY, mQuery)
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
        }

        return true
    }

    override fun showNews(articles: List<Article>) {
        setVisibility(container = true)
        mNewsAdapter.updateData(articles)
    }

    override fun showError() {
        setVisibility(empty = true)
        mNewsAdapter.clearData();
    }

    fun showLoading() {
        setVisibility(loading = true)
        mNewsAdapter.clearData();
    }

    override fun onClick(articleUrl: String) {
        intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra(WebViewActivity.ARTICLE_URL_EXTRA, articleUrl)
        startActivity(intent)
    }

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
