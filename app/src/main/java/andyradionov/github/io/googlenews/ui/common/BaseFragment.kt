package andyradionov.github.io.googlenews.ui.common

import android.content.Intent
import andyradionov.github.io.googlenews.ui.details.WebViewActivity
import andyradionov.github.io.googlenews.ui.topnews.NewsAdapter
import com.arellomobile.mvp.MvpAppCompatFragment

/**
 * @author Andrey Radionov
 */
abstract class BaseFragment : MvpAppCompatFragment() {

    protected val onArticleClickListener = object : NewsAdapter.OnArticleClickListener {
        override fun onClick(articleUrl: String) {
            val intent = Intent(activity, WebViewActivity::class.java)
            intent.putExtra(WebViewActivity.ARTICLE_URL_EXTRA, articleUrl)
            startActivity(intent)
        }
    }

//    override fun onAttach(context: Context) {
//        App.appComponent.inject(this)
//        super.onAttach(context)
//    }
}