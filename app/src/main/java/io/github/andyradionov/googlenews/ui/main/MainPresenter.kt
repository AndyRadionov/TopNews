package io.github.andyradionov.googlenews.ui.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
@InjectViewState
class MainPresenter @Inject constructor(
        private val router: Router) :
        MvpPresenter<MvpView>() {

    fun selectTab(screen: Screen) {
        router.navigateTo(screen)
    }
}
