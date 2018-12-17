package io.github.andyradionov.googlenews.ui.common.adapter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.github.andyradionov.googlenews.app.Screens
import io.github.andyradionov.googlenews.data.entities.Article
import io.github.andyradionov.googlenews.data.message.SystemMessageNotifier
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
@InjectViewState
class NewsAdapterPresenter @Inject constructor(
        private val systemMessageNotifier: SystemMessageNotifier,
        private val router: Router):
        MvpPresenter<MvpView>() {

    fun showBottomDialog(article: Article) {
        systemMessageNotifier.send(article)
    }

    fun openDetailsScreen(article: Article) {
        router.navigateTo(Screens.DetailsScreen(article))
    }
}