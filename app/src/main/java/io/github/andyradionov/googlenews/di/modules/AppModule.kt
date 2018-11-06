package io.github.andyradionov.googlenews.di.modules

import android.support.annotation.NonNull
import dagger.Module
import dagger.Provides
import io.github.andyradionov.googlenews.data.datasource.remote.NewsApi
import io.github.andyradionov.googlenews.data.repositories.NewsRepository
import io.github.andyradionov.googlenews.ui.headlines.HeadlinesPresenter
import io.github.andyradionov.googlenews.ui.search.SearchPresenter
import io.github.andyradionov.googlenews.ui.topnews.TopNewsPresenter
import javax.inject.Singleton

/**
 * @author Andrey Radionov
 */

@Module
class AppModule {


    @NonNull
    @Provides
    @Singleton
    fun provideTopNewsPresenter(newsRepository: NewsRepository) = TopNewsPresenter(newsRepository)

    @NonNull
    @Provides
    fun provideHeadlinesPresenter(newsRepository: NewsRepository) = HeadlinesPresenter(newsRepository)

    @NonNull
    @Provides
    @Singleton
    fun provideSearchPresenter(newsRepository: NewsRepository) = SearchPresenter(newsRepository)

    @NonNull
    @Provides
    @Singleton
    fun provideRemoteNewsRepository(newsApi: NewsApi) = NewsRepository(newsApi)
}
