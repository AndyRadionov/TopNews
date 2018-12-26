package io.github.andyradionov.topnews.ui.details

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.github.andyradionov.topnews.R
import io.github.andyradionov.topnews.data.entities.Article
import io.github.andyradionov.topnews.ui.common.BaseActivity
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
class DetailsWebViewActivity : BaseActivity(), DetailsView {

    @Inject
    @InjectPresenter
    lateinit var presenter: DetailsPresenter

    @ProvidePresenter
    fun providePresenter(): DetailsPresenter = presenter

    private lateinit var webView: WebView
    private lateinit var article: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        article = intent.getParcelableExtra(ARTICLE_EXTRA)

        title = Uri.parse(article.url).host
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        webView = WebView(this)
        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                view.loadUrl(request.url.toString())
                return true
            }
        }
        webView.loadUrl(article.url)

        setContentView(webView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        if (article.isFavourite) {
            menu.findItem(R.id.action_favourite).title = getString(R.string.action_remove)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            android.R.id.home -> performAction { presenter.onBackPressed() }
            R.id.action_favourite -> performAction { presenter.onFavourites(article) }
            R.id.action_share -> performAction { presenter.shareArticle(article) }
            R.id.action_open_browser -> performAction { presenter.openInBrowser(article.url) }
            R.id.action_copy_link -> performAction { presenter.copyLink(article.url) }
            else -> super.onOptionsItemSelected(menuItem)
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            presenter.onBackPressed()
        }
    }

    override fun showToast(msgId: Int) {
        Toast.makeText(this, msgId, Toast.LENGTH_SHORT).show()
    }

    override fun onArticleFavourite(msgId: Int) {
        invalidateOptionsMenu()
        showToast(msgId)
    }

    private fun performAction(action: () -> Unit): Boolean {
        action()
        return true
    }

    companion object {
        const val ARTICLE_EXTRA = "article_extra"
    }
}
