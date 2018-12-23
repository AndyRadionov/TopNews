package io.github.andyradionov.googlenews.ui.common

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
open class MenuPresenter<T: MvpView> @Inject constructor(
        private val router: Router,
        private val newsInteractor: NewsInteractor,
        private val clipboardManager: ClipboardManager
): BasePresenter<T>() {

    open fun onFavourites(article: Article) {
        dispose()
        if (!article.isFavourite) {
            addToFavourites(article) { messageNotifier.send("Article Added") }
        } else {
            removeFromFavourites(article) { messageNotifier.send("Article Removed") }
        }
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

    open fun copyLink(url: String) {
        copyLink(url) { /*todo*/messageNotifier.send("Copied") }
    }

    protected fun copyLink(url: String, onCopy: () -> Unit) {
        val clipData = ClipData.newPlainText(TEXT_TYPE, url)
        clipboardManager.primaryClip = clipData
        onCopy()
    }

    protected fun addToFavourites(article: Article, onSubscribe: () -> Unit) {
        article.isFavourite = true
        disposable = newsInteractor.addToFavourites(article)
                .compose(rxComposers.getCompletableComposer())
                .subscribe { onSubscribe() }
    }

    protected fun removeFromFavourites(article: Article, onSubscribe: () -> Unit) {
        disposable = newsInteractor.removeFromFavourites(article)
                .compose(rxComposers.getCompletableComposer())
                //todo
                .subscribe { onSubscribe() }
    }
}
