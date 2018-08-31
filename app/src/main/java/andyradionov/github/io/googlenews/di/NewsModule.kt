package andyradionov.github.io.googlenews.di

import android.support.annotation.NonNull
import andyradionov.github.io.googlenews.BuildConfig
import andyradionov.github.io.googlenews.app.API_KEY
import andyradionov.github.io.googlenews.app.BASE_URL
import andyradionov.github.io.googlenews.data.NewsApi
import andyradionov.github.io.googlenews.data.NewsRepository
import andyradionov.github.io.googlenews.ui.news.NewsContract
import andyradionov.github.io.googlenews.ui.news.NewsPresenter
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
    fun provideNewsPresenter(newsRepository: NewsRepository): NewsContract.Presenter
            = NewsPresenter(newsRepository)

    @NonNull
    @Provides
    @Singleton
    fun provideNewsStore(newsApi: NewsApi) = NewsRepository(newsApi)

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
                                    .addQueryParameter("apiKey", BuildConfig.ApiKey)
                                    .build())
                            .build()

                    chain.proceed(request)
                }.build()
    }
}