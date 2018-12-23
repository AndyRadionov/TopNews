package io.github.andyradionov.googlenews.ui.menu

import android.content.ClipboardManager
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpView
import io.github.andyradionov.googlenews.interactors.NewsInteractor
import io.github.andyradionov.googlenews.ui.common.MenuPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
@InjectViewState
open class NewsBottomSheetPresenter @Inject constructor(
        router: Router,
        newsInteractor: NewsInteractor,
        clipboardManager: ClipboardManager
): MenuPresenter<MvpView>(router, newsInteractor, clipboardManager)
