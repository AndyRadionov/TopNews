package io.github.andyradionov.googlenews

import android.content.Context
import android.content.Intent
import android.net.Uri
import io.github.andyradionov.googlenews.data.entities.Article
import io.github.andyradionov.googlenews.ui.details.DetailsWebViewActivity
import io.github.andyradionov.googlenews.ui.favourites.FavouritesFragment
import io.github.andyradionov.googlenews.ui.headlines.HeadlinesFragment
import io.github.andyradionov.googlenews.ui.topnews.TopNewsFragment
import io.github.andyradionov.googlenews.utils.TEXT_TYPE
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

    data class DetailsScreen(val article: Article): SupportAppScreen() {
        override fun getActivityIntent(context: Context?): Intent {
            val intent = Intent(context, DetailsWebViewActivity::class.java)
            intent.putExtra(DetailsWebViewActivity.ARTICLE_EXTRA, article)
            return intent
        }
    }

    data class ExternalBrowserFlow(val url: String) : SupportAppScreen() {
        override fun getActivityIntent(context: Context?) =
                Intent(Intent.ACTION_VIEW, Uri.parse(url))
    }

    data class ShareFlow(val article: Article) : SupportAppScreen() {
        override fun getActivityIntent(context: Context) =
                Intent.createChooser(
                        Intent(Intent.ACTION_SEND).apply {
                            putExtra(Intent.EXTRA_TEXT, article.url)
                            type = TEXT_TYPE
                        },
                        context.getString(R.string.share, article.title)
                )
    }
}
