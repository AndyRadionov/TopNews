package io.github.andyradionov.googlenews.di.modules

import android.support.annotation.NonNull
import dagger.Module
import dagger.Provides
import io.github.andyradionov.googlenews.data.datasource.remote.NewsApi
import io.github.andyradionov.googlenews.data.repositories.NewsRepository
import io.github.andyradionov.googlenews.interactors.NewsInteractor
import io.github.andyradionov.googlenews.ui.headlines.HeadlinesPresenter
import io.github.andyradionov.googlenews.ui.search.SearchPresenter
import io.github.andyradionov.googlenews.ui.topnews.TopNewsPresenter
import io.github.andyradionov.googlenews.utils.RxComposers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

/**
 * @author Andrey Radionov
 */

@Module
class AppModule {

    @NonNull
    @Provides
    @Singleton
    fun provideTopNewsPresenter(interactor: NewsInteractor, rxComposers: RxComposers) =
            TopNewsPresenter(interactor, rxComposers)

    @NonNull
    @Provides
    fun provideHeadlinesPresenter(interactor: NewsInteractor, rxComposers: RxComposers) =
            HeadlinesPresenter(interactor, rxComposers)

    @NonNull
    @Provides
    @Singleton
    fun provideSearchPresenter(interactor: NewsInteractor, rxComposers: RxComposers) =
            SearchPresenter(interactor, rxComposers)

    @NonNull
    @Provides
    @Singleton
    fun provideNewsInteractor(repository: NewsRepository) = NewsInteractor(repository)

    @NonNull
    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi) = NewsRepository(newsApi)

    @NonNull
    @Provides
    @Singleton
    fun provideRxSchedulers() = RxComposers(Schedulers.io(), AndroidSchedulers.mainThread())
}
