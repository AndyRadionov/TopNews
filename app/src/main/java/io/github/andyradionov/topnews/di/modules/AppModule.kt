package io.github.andyradionov.topnews.di.modules

import android.app.Application
import android.content.ClipboardManager
import android.content.Context
import android.support.annotation.NonNull
import dagger.Module
import dagger.Provides
import io.github.andyradionov.topnews.data.datasource.local.NewsDao
import io.github.andyradionov.topnews.data.datasource.server.NewsApi
import io.github.andyradionov.topnews.data.message.SystemMessageNotifier
import io.github.andyradionov.topnews.data.repositories.NewsRepository
import io.github.andyradionov.topnews.interactors.NewsInteractor
import io.github.andyradionov.topnews.utils.RxComposers
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
    fun provideNewsInteractor(repository: NewsRepository) = NewsInteractor(repository)

    @NonNull
    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi, newsDao: NewsDao) = NewsRepository(newsApi, newsDao)

    @NonNull
    @Provides
    @Singleton
    fun provideRxComposers() = RxComposers(Schedulers.io(), AndroidSchedulers.mainThread())

    @NonNull
    @Provides
    @Singleton
    fun provideMessageNotifier() = SystemMessageNotifier()

    @NonNull
    @Provides
    @Singleton
    fun provideClipboardManager(app: Application) =
            app.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
}
