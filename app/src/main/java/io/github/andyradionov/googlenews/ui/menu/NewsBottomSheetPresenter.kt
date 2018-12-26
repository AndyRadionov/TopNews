package io.github.andyradionov.googlenews.ui.menu

import android.content.ClipboardManager
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpView
import io.github.andyradionov.googlenews.R
import io.github.andyradionov.googlenews.data.entities.Article
import io.github.andyradionov.googlenews.interactors.NewsInteractor
import io.github.andyradionov.googlenews.ui.common.MenuPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
@InjectViewState
class NewsBottomSheetPresenter @Inject constructor(
        router: Router,
        newsInteractor: NewsInteractor,
        clipboardManager: ClipboardManager
) : MenuPresenter<MvpView>(router, newsInteractor, clipboardManager) {

    override fun onFavourites(article: Article) {
        dispose()
        if (!article.isFavourite) {
            addToFavourites(article) { messageNotifier.send(R.string.article_saved) }
        } else {
            removeFromFavourites(article) { messageNotifier.send(R.string.article_removed) }
        }
    }

    override fun copyLink(url: String) {
        copyLink(url) { messageNotifier.send(R.string.link_copied) }
    }
}
