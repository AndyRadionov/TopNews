package io.github.andyradionov.googlenews.ui.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.github.andyradionov.googlenews.R
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
class DetailsWebViewActivity: AppCompatActivity() {


    @Inject
    @InjectPresenter
    lateinit var presenter: DetailsPresenter

    @ProvidePresenter
    fun providePresenter(): DetailsPresenter = presenter

    private var mWebView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)

        val siteUrl = intent.getStringExtra(ARTICLE_URL_EXTRA)

        title = getString(R.string.web_view_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mWebView = WebView(this)
        mWebView?.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                view.loadUrl(request.url.toString())
                return true
            }
        }
        mWebView?.loadUrl(siteUrl)

        setContentView(mWebView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        Log.d(TAG, "onOptionsItemSelected")

        if (android.R.id.home == menuItem.itemId) {
            presenter.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(menuItem)
    }

    override fun onBackPressed() {
        Log.d(TAG, "onBackPressed")

        if (mWebView?.canGoBack() == true) {
            mWebView?.goBack()
        } else {
            presenter.onBackPressed()
        }
    }

    companion object {
        private val TAG = DetailsWebViewActivity::class.java.simpleName

        const val ARTICLE_URL_EXTRA = "article_url_extra"
    }
}