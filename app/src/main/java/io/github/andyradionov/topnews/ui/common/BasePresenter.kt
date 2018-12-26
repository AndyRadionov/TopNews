package io.github.andyradionov.topnews.ui.common

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.github.andyradionov.topnews.R
import io.github.andyradionov.topnews.data.message.SystemMessageNotifier
import io.github.andyradionov.topnews.utils.NetworkManager
import io.github.andyradionov.topnews.utils.RxComposers
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
            messageNotifier.send(R.string.error_no_internet)
            return true
        }
        return false
    }

    protected fun dispose() {
        disposable?.dispose()
    }
}