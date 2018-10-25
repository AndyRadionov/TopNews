package andyradionov.github.io.googlenews.di

import andyradionov.github.io.googlenews.ui.news.NewsActivity
import dagger.Component
import javax.inject.Singleton

/**
 * @author Andrey Radionov
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(newsActivity: NewsActivity)
}