package andyradionov.github.io.googlenews.di

import andyradionov.github.io.googlenews.ui.common.BaseFragment
import andyradionov.github.io.googlenews.ui.MainActivity
import andyradionov.github.io.googlenews.ui.news.TopNewsFragment
import dagger.Component
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