package io.github.andyradionov.googlenews.ui.common

import android.content.ClipData
import android.content.ClipboardManager
import com.arellomobile.mvp.MvpView
import io.github.andyradionov.googlenews.Screens
import io.github.andyradionov.googlenews.data.entities.Article
import io.github.andyradionov.googlenews.interactors.NewsInteractor
import io.github.andyradionov.googlenews.utils.TEXT_TYPE
import ru.terrakok.cicerone.Router

/**
 * @author Andrey Radionov
 */
abstract class MenuPresenter<T : MvpView> (
        private val router: Router,
        private val newsInteractor: NewsInteractor,
        private val clipboardManager: ClipboardManager
) : BasePresenter<T>() {

    fun shareArticle(article: Article) {
        router.navigateTo(Screens.ShareFlow(article))
    }

    fun openInBrowser(url: String) {
        router.navigateTo(Screens.ExternalBrowserFlow(url))
    }

    fun readArticle(article: Article) {
        router.navigateTo(Screens.DetailsScreen(article))
    }

    abstract fun onFavourites(article: Article)

    abstract fun copyLink(url: String)

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
        article.isFavourite = false
        disposable = newsInteractor.removeFromFavourites(article)
                .compose(rxComposers.getCompletableComposer())
                .subscribe { onSubscribe() }
    }
}
