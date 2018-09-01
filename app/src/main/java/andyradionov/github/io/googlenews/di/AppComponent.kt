package andyradionov.github.io.googlenews.di

import andyradionov.github.io.googlenews.app.App
import andyradionov.github.io.googlenews.data.NewsRepository
import andyradionov.github.io.googlenews.ui.news.NewsActivity
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * @author Andrey Radionov
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class, NewsActivityModule::class, NewsModule::class])
interface AppComponent {
    fun inject(app: App)
}