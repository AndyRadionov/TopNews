package io.github.andyradionov.googlenews.ui.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.github.andyradionov.googlenews.data.message.SystemMessageNotifier
import io.reactivex.disposables.Disposable
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
@InjectViewState
class MainPresenter @Inject constructor(
        private val router: Router,
        private val systemMessageNotifier: SystemMessageNotifier) :
        MvpPresenter<MainView>() {

    private var notifierDisposable: Disposable? = null

    fun selectTab(screen: Screen) {
        router.navigateTo(screen)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        subscribeOnSystemMessages()
    }

    override fun onDestroy() {
        notifierDisposable?.dispose()
    }

    private fun subscribeOnSystemMessages() {
        notifierDisposable = systemMessageNotifier.notifier
                .subscribe { msg ->
                    viewState.showBottomSheet(msg.article)
                }
    }
}
