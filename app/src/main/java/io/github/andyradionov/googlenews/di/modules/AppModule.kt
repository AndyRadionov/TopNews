package io.github.andyradionov.googlenews.di.modules

import android.support.annotation.NonNull
import android.util.Log
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.github.andyradionov.googlenews.BuildConfig
import io.github.andyradionov.googlenews.app.NewsApp
import io.github.andyradionov.googlenews.data.NewsApi
import io.github.andyradionov.googlenews.data.NewsRepository
import io.github.andyradionov.googlenews.ui.topnews.TopNewsPresenter
import io.github.andyradionov.googlenews.utils.isInternetAvailable
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author Andrey Radionov
 */

@Module
class AppModule(val newsApp: NewsApp) {

    @NonNull
    @Provides
    @Singleton
    fun provideTopNewsPresenter(newsRepository: NewsRepository) = TopNewsPresenter(newsRepository)

    @NonNull
    @Provides
    @Singleton
    fun provideRemoteNewsRepository(newsApi: NewsApi) = NewsRepository(newsApi)
}
