package andyradionov.github.io.googlenews.news

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import andyradionov.github.io.googlenews.R
import andyradionov.github.io.googlenews.app.App
import andyradionov.github.io.googlenews.data.Article
import javax.inject.Inject

import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity(), NewsContract.View, NewsAdapter.OnArticleClickListener {

    @Inject lateinit var mPresenter: NewsContract.Presenter
    private lateinit var mNewsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        App.sAppComponent.inject(this)

        setUpRecycler()
        mPresenter.attachView(this)
        mPresenter.getTopNews()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)

        val searchAction = menu.findItem(R.id.action_search)

        val searchView = searchAction.actionView as SearchView
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mPresenter.searchNews(query)
                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }
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
                return true
            }
        })

        return true
    }

    override fun showNews(articles: List<Article>) {
        mNewsAdapter.updateData(articles)
    }

    override fun showError() {
        mNewsAdapter.clearData();
    }

    override fun onClick(articleUrl: String?) {
        TODO("not implemented")
    }

    private fun setUpRecycler() {
        mNewsAdapter = NewsAdapter(this)
        rv_news_container.adapter = mNewsAdapter

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_news_container.layoutManager = layoutManager
    }
}
