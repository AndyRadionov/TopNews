package io.github.andyradionov.googlenews.ui.dialogs

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpView
import io.github.andyradionov.googlenews.app.Screens
import io.github.andyradionov.googlenews.data.entities.Article
import io.github.andyradionov.googlenews.interactors.NewsInteractor
import io.github.andyradionov.googlenews.ui.common.BasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
@InjectViewState
class NewsBottomSheetPresenter @Inject constructor(
        private val router: Router,
        private val newsInteractor: NewsInteractor
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
                .subscribe { messageNotifier.send("Article Removed") }
    }

    fun shareArticle(article: Article) {
        router.navigateTo(Screens.ShareFlow(article))
    }
}