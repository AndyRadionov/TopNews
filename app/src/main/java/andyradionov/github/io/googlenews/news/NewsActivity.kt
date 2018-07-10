package andyradionov.github.io.googlenews.news

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
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
        mPresenter.attachView(this)
        setUpRecycler()
        mPresenter.getTopNews()
    }

    override fun showNews(articles: List<Article>) {
        mNewsAdapter.updateData(articles)
    }

    override fun showError() {
        mNewsAdapter.clearData();
    }

    override fun onClick(articleUrl: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setUpRecycler() {
        mNewsAdapter = NewsAdapter(this)
        rv_news_container.adapter = mNewsAdapter

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_news_container.layoutManager = layoutManager
    }
}
