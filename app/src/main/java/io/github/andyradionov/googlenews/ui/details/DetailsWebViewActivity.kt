package io.github.andyradionov.googlenews.ui.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.github.andyradionov.googlenews.R
import io.github.andyradionov.googlenews.data.entities.Article
import io.github.andyradionov.googlenews.ui.common.BaseActivity
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
class DetailsWebViewActivity: BaseActivity(), MvpView {

    @Inject
    @InjectPresenter
    lateinit var presenter: DetailsPresenter

    @ProvidePresenter
    fun providePresenter(): DetailsPresenter = presenter

    private var webView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val article = intent.getParcelableExtra<Article>(ARTICLE_EXTRA)

        title = getString(R.string.web_view_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        webView = WebView(this)
        webView?.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                view.loadUrl(request.url.toString())
                return true
            }
        }
        webView?.loadUrl(article.url)

        setContentView(webView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (android.R.id.home == menuItem.itemId) {
            presenter.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(menuItem)
    }

    override fun onBackPressed() {
        if (webView?.canGoBack() == true) {
            webView?.goBack()
        } else {
            presenter.onBackPressed()
        }
    }

    companion object {
        const val ARTICLE_EXTRA = "article_extra"
    }
}
