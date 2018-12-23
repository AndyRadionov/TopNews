package io.github.andyradionov.googlenews.ui.dialogs

import android.content.ClipData
import android.content.ClipboardManager
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpView
import io.github.andyradionov.googlenews.app.Screens
import io.github.andyradionov.googlenews.data.entities.Article
import io.github.andyradionov.googlenews.interactors.NewsInteractor
import io.github.andyradionov.googlenews.ui.common.BasePresenter
import io.github.andyradionov.googlenews.utils.TEXT_TYPE
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
@InjectViewState
class NewsBottomSheetPresenter @Inject constructor(
        private val router: Router,
        private val newsInteractor: NewsInteractor,
        private val clipboardManager: ClipboardManager
): BasePresenter<MvpView>() {

    fun addToFavourites(article: Article) {
        dispose()
        article.isFavourite = true
        disposable = newsInteractor.addToFavourites(article)
                .compose(rxComposers.getCompletableComposer())
                .subscribe { messageNotifier.send("Article Added") }
    }

    fun removeFromFavourites(article: Article) {
        dispose()
        disposable = newsInteractor.removeFromFavourites(article)
                .compose(rxComposers.getCompletableComposer())
                //todo
                .subscribe { messageNotifier.send("Article Removed") }
    }

    fun shareArticle(article: Article) {
        router.navigateTo(Screens.ShareFlow(article))
    }

    fun openInBrowser(url: String) {
        router.navigateTo(Screens.ExternalBrowserFlow(url))
    }

    fun readArticle(article: Article) {
        router.navigateTo(Screens.DetailsScreen(article))
    }

    fun copyLink(url: String) {
        val clipData = ClipData.newPlainText(TEXT_TYPE, url)
        clipboardManager.primaryClip = clipData
        //todo
        messageNotifier.send("Copied")
    }
}
