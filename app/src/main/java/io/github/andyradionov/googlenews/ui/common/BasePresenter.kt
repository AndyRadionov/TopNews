package io.github.andyradionov.googlenews.ui.common

import com.arellomobile.mvp.MvpPresenter
import io.github.andyradionov.googlenews.ui.common.views.BaseView
import io.github.andyradionov.googlenews.utils.NetworkManager
import io.github.andyradionov.googlenews.utils.RxComposers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
abstract class BasePresenter<T : BaseView> : MvpPresenter<T>() {

    @Inject lateinit var rxComposers: RxComposers
    @Inject lateinit var networkManager: NetworkManager
    protected var disposable: Disposable? = null

    override fun onDestroy() {
        super.onDestroy()
        dispose()
        disposable = null
    }

    protected fun checkNotConnected(): Boolean {
        dispose()
        if (!networkManager.isInternetAvailable()) {
            viewState.showNotConnected()
            return true
        }
        return false
    }

    private fun dispose() {
        disposable?.dispose()
    }
}