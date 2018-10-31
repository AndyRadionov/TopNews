package io.github.andyradionov.googlenews.di

import dagger.Component
import io.github.andyradionov.googlenews.ui.MainActivity
import io.github.andyradionov.googlenews.ui.common.BaseFragment
import io.github.andyradionov.googlenews.ui.topnews.TopNewsFragment
import javax.inject.Singleton

/**
 * @author Andrey Radionov
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(baseFragment: BaseFragment)
    fun inject(topNewsFragment: TopNewsFragment)
}