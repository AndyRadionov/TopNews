package io.github.andyradionov.googlenews.app

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.app.Fragment
import io.github.andyradionov.googlenews.ui.details.DetailsWebViewActivity
import io.github.andyradionov.googlenews.ui.favourites.FavouritesFragment
import io.github.andyradionov.googlenews.ui.headlines.HeadlinesFragment
import io.github.andyradionov.googlenews.ui.topnews.TopNewsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

/**
 * @author Andrey Radionov
 */
object Screens {

    object TopNewsScreen: SupportAppScreen() {
        override fun getFragment() = TopNewsFragment()
    }

    object HeadlinesScreen: SupportAppScreen() {
        override fun getFragment() = HeadlinesFragment()
    }

    object FavouritesScreen: SupportAppScreen() {
        override fun getFragment() = FavouritesFragment()
    }

    data class DetailsScreen(val articleUrl: String): SupportAppScreen() {
        override fun getActivityIntent(context: Context?): Intent {
            val intent = Intent(context, DetailsWebViewActivity::class.java)
            intent.putExtra(DetailsWebViewActivity.ARTICLE_URL_EXTRA, articleUrl)
            return intent
        }
    }
}