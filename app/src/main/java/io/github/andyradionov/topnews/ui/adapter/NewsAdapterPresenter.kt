package io.github.andyradionov.topnews.ui.adapter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.github.andyradionov.topnews.Screens
import io.github.andyradionov.topnews.data.entities.Article
import io.github.andyradionov.topnews.data.message.SystemMessageNotifier
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