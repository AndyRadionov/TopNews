package io.github.andyradionov.googlenews.ui.common

import android.content.Context
import android.content.Intent
import com.arellomobile.mvp.MvpAppCompatFragment
import dagger.android.support.AndroidSupportInjection
import io.github.andyradionov.googlenews.ui.details.DetailsWebViewActivity
import io.github.andyradionov.googlenews.ui.dialogs.NewsBottomSheetDialog
import io.github.andyradionov.googlenews.ui.topnews.NewsAdapter

/**
 * @author Andrey Radionov
 */
abstract class BaseFragment : MvpAppCompatFragment() {

    protected val onArticleClickListener = object : NewsAdapter.OnArticleClickListener {
        override fun onClick(articleUrl: String) {
            val intent = Intent(activity, DetailsWebViewActivity::class.java)
            intent.putExtra(DetailsWebViewActivity.ARTICLE_URL_EXTRA, articleUrl)
            startActivity(intent)
        }

        override fun onOpenDialogClick(articleUrl: String) {
            NewsBottomSheetDialog()
                    .show(fragmentManager, NewsBottomSheetDialog.TAG)
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}