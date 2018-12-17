package io.github.andyradionov.googlenews.ui.main

import com.arellomobile.mvp.MvpView
import io.github.andyradionov.googlenews.data.entities.Article

/**
 * @author Andrey Radionov
 */
interface MainView: MvpView {

    fun showBottomSheet(article: Article)
}
