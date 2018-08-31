package andyradionov.github.io.googlenews.di

import andyradionov.github.io.googlenews.data.NewsRepository
import andyradionov.github.io.googlenews.ui.news.NewsActivity
import dagger.Component
import javax.inject.Singleton

/**
 * @author Andrey Radionov
 */
@Singleton
@Component(modules = [NewsModule::class])
interface AppComponent {

    fun inject(newsRepository: NewsRepository)

    fun inject(activity: NewsActivity)
}