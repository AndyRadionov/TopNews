package io.github.andyradionov.googlenews.ui.common

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.github.andyradionov.googlenews.data.message.SystemMessageNotifier
import io.github.andyradionov.googlenews.utils.NetworkManager
import io.github.andyradionov.googlenews.utils.RxComposers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
abstract class BasePresenter<T : MvpView> : MvpPresenter<T>() {

    @Inject lateinit var rxComposers: RxComposers
    @Inject lateinit var networkManager: NetworkManager
    @Inject lateinit var messageNotifier: SystemMessageNotifier

    protected var disposable: Disposable? = null

    override fun onDestroy() {
        super.onDestroy()
        dispose()
        disposable = null
    }

    protected fun checkNotConnected(): Boolean {
        dispose()
        if (!networkManager.isInternetAvailable()) {
            messageNotifier.send("Not Connected")
            return true
        }
        return false
    }

    protected fun dispose() {
        disposable?.dispose()
    }
}