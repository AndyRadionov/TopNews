package andyradionov.github.io.googlenews.app.di

import andyradionov.github.io.googlenews.data.NewsStore
import andyradionov.github.io.googlenews.news.NewsActivity
import andyradionov.github.io.googlenews.news.NewsContract
import andyradionov.github.io.googlenews.news.NewsPresenter
import dagger.Component
import javax.inject.Singleton

/**
 * @author Andrey Radionov
 */
@Singleton
@Component(modules = [NewsModule::class])
interface AppComponent {

    fun inject(newsStore: NewsStore)

    fun inject(activity: NewsActivity)
}