package andyradionov.github.io.googlenews.ui.news

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import andyradionov.github.io.googlenews.R
import andyradionov.github.io.googlenews.app.App
import andyradionov.github.io.googlenews.data.entities.Article
import andyradionov.github.io.googlenews.ui.details.WebViewActivity
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_news.*
import javax.inject.Inject

class NewsActivity : MvpAppCompatActivity(), NewsView {

    @Inject
    @InjectPresenter
    lateinit var presenter: NewsPresenter
    private lateinit var newsAdapter: NewsAdapter
    private var query: String = EMPTY_QUERY
    private var listPosition = 0

    private val mOnArticleClickListener = object : NewsAdapter.OnArticleClickListener {
        override fun onClick(articleUrl: String) {
            intent = Intent(this@NewsActivity, WebViewActivity::class.java)
            intent.putExtra(WebViewActivity.ARTICLE_URL_EXTRA, articleUrl)
            startActivity(intent)
        }
    }

    @ProvidePresenter
    fun providePresenter(): NewsPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        setUpSwipeRefresh()
        setUpRecycler()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(QUERY_KEY, query)
        listPosition = (rv_news_container.layoutManager as LinearLayoutManager)
                .findFirstVisibleItemPosition()
        outState.putInt(LIST_POSITION_KEY, listPosition)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        query = savedInstanceState.getString(QUERY_KEY)
        listPosition = savedInstanceState.getInt(LIST_POSITION_KEY)
        rv_news_container.scrollToPosition(listPosition)
    }

    override fun onResume() {
        super.onResume()
        if (newsAdapter.itemCount == 0) {
            loadNews(query)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)

        val searchAction = menu.findItem(R.id.action_search)

        val searchView = searchAction.actionView as SearchView
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                loadNews(query)
                searchView.clearFocus()
                return true
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
                loadNews(EMPTY_QUERY)
                invalidateOptionsMenu()
                return true
            }
        })

        if (!query.isEmpty()) {
            searchAction.expandActionView()
            searchView.setQuery(query, true)
            searchView.clearFocus()
        }

        return true
    }

    override fun showNews(articles: List<Article>) {
        setVisibility(container = true)
        newsAdapter.updateData(articles)
    }

    override fun showError() {
        setVisibility(empty = true)
        newsAdapter.clearData();
    }

    override fun showLoading() {
        setVisibility(loading = true)
        newsAdapter.clearData();
    }

    private fun setUpSwipeRefresh() {
        swipe_container.setOnRefreshListener {
            swipe_container.isRefreshing = false
            presenter.fetchNews(query)
        }
    }

    private fun setUpRecycler() {
        newsAdapter = NewsAdapter(mOnArticleClickListener)
        rv_news_container.adapter = newsAdapter

        val columnsNumber = resources.getInteger(R.integer.columns_number)
        val layoutManager = GridLayoutManager(this, columnsNumber)

        rv_news_container.layoutManager = layoutManager
    }

    private fun setVisibility(container: Boolean = false,
                              loading: Boolean = false,
                              empty: Boolean = false) {

        rv_news_container.visibility = if (container) View.VISIBLE else View.INVISIBLE
        swipe_container.isRefreshing = loading
        tv_empty_view.visibility = if (empty) View.VISIBLE else View.INVISIBLE
    }

    private fun loadNews(query: String) {
        showLoading()
        this.query = query
        presenter.fetchNews(query)
    }

    companion object {
        const val EMPTY_QUERY = ""
        const val QUERY_KEY = "query_key"
        const val LIST_POSITION_KEY = "list_position_key"
    }
}
