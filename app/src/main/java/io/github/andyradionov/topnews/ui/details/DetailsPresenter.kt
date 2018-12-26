package io.github.andyradionov.topnews.ui.details

import android.content.ClipboardManager
import com.arellomobile.mvp.InjectViewState
import io.github.andyradionov.topnews.R
import io.github.andyradionov.topnews.data.entities.Article
import io.github.andyradionov.topnews.interactors.NewsInteractor
import io.github.andyradionov.topnews.ui.common.MenuPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
@InjectViewState
class DetailsPresenter @Inject constructor(
        val router: Router,
        newsInteractor: NewsInteractor,
        clipboardManager: ClipboardManager
) : MenuPresenter<DetailsView>(router, newsInteractor, clipboardManager) {

    fun onBackPressed() {
        router.exit()
    }

    override fun onFavourites(article: Article) {
        dispose()
        if (!article.isFavourite) {
            addToFavourites(article) { viewState.onArticleFavourite(R.string.article_saved) }
        } else {
            removeFromFavourites(article) { viewState.onArticleFavourite(R.string.article_removed) }
        }
    }

    override fun copyLink(url: String) {
        copyLink(url) { viewState.showToast(R.string.link_copied) }
    }
}
