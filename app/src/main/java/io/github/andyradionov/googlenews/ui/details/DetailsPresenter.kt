package io.github.andyradionov.googlenews.ui.details

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
@InjectViewState
class DetailsPresenter @Inject constructor(
        private val router: Router):
        MvpPresenter<MvpView>() {

    fun onBackPressed() {
        router.exit()
    }
}
