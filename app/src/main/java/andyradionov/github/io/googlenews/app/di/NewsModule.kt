package andyradionov.github.io.googlenews.app.di

import android.content.Context
import android.support.annotation.NonNull
import android.util.Log
import andyradionov.github.io.googlenews.app.API_KEY
import andyradionov.github.io.googlenews.app.BASE_URL
import andyradionov.github.io.googlenews.data.NewsApi
import andyradionov.github.io.googlenews.data.NewsStore
import andyradionov.github.io.googlenews.news.NewsContract
import andyradionov.github.io.googlenews.news.NewsPresenter
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @author Andrey Radionov
 */

@Module
class NewsModule {

    @NonNull
    @Provides
    @Singleton
    fun provideNewsPresenter(): NewsContract.Presenter {
        return NewsPresenter()
    }

    @NonNull
    @Provides
    @Singleton
    fun provideNewsStore(): NewsStore {
        return NewsStore()
    }

    @NonNull
    @Provides
    @Singleton
    fun provideNewsApi(httpClient: OkHttpClient): NewsApi {

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()
                .create(NewsApi::class.java)
    }

    @NonNull
    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()

                    val request = original.newBuilder().url(
                            original.url()
                                    .newBuilder()
                                    .addQueryParameter("apiKey", API_KEY)
                                    .build())
                            .build()

                    chain.proceed(request)
                }.build()
    }
}