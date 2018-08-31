package andyradionov.github.io.googlenews.ui.details

import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.webkit.WebResourceRequest
import andyradionov.github.io.googlenews.R


/**
 * @author Andrey Radionov
 */
class WebViewActivity: AppCompatActivity() {

    companion object {
        private val TAG = WebViewActivity::class.java.simpleName

        const val ARTICLE_URL_EXTRA = "article_url_extra"
    }


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

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        Log.d(TAG, "onOptionsItemSelected")

        if (android.R.id.home == menuItem.itemId) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(menuItem)
    }

    override fun onBackPressed() {
        Log.d(TAG, "onBackPressed")

        if (mWebView?.canGoBack() == true) {
            mWebView?.goBack()
        } else {
            finish()
        }
    }
}