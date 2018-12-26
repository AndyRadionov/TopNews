package io.github.andyradionov.topnews.di.modules

import android.app.Application
import android.support.annotation.NonNull
import android.util.Log
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.github.andyradionov.topnews.BuildConfig.API_KEY
import io.github.andyradionov.topnews.BuildConfig.API_URL
import io.github.andyradionov.topnews.data.datasource.server.NewsApi
import io.github.andyradionov.topnews.utils.NetworkManager
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
class NetworkModule {

    @NonNull
    @Provides
    @Singleton
    fun provideNewsApi(httpClient: OkHttpClient): NewsApi {

        return Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()
                .create(NewsApi::class.java)
    }

    @NonNull
    @Provides
    @Singleton
    fun provideOkHttp(app: Application, networkManager: NetworkManager): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(initApiKeyInterceptor())
                .addInterceptor(initOfflineCacheInterceptor(networkManager))
                .addNetworkInterceptor(initCacheInterceptor())
                .cache(initCache(app))
                .build()
    }

    @NonNull
    @Provides
    @Singleton
    fun provideNetworkManager(app: Application) =
            NetworkManager(app.applicationContext)

    private fun initApiKeyInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()

            val request = original.newBuilder().url(
                    original.url()
                            .newBuilder()
                            .addQueryParameter("apiKey", API_KEY)
                            .build())
                    .build()

            chain.proceed(request)
        }
    }

    private fun initCache(app: Application): Cache? {
        var cache: Cache? = null
        try {
            cache = Cache(File(app.cacheDir, "http-cache"), (CACHE_SIZE).toLong())
        } catch (e: Exception) {
            Log.e(TAG, "Could not create Cache!")
        }

        return cache
    }

    private fun initCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())

            // re-write response header to force use of cache
            val cacheControl = CacheControl.Builder()
                    .maxAge(MAX_AGE, TimeUnit.MINUTES)
                    .build()

            response.newBuilder()
                    .removeHeader(PRAGMA_HEADER)
                    .removeHeader(ACCESS_CONTROL_ALLOW_ORIGIN_HEADER)
                    .removeHeader(VARY_HEADER)
                    .removeHeader(CACHE_CONTROL_HEADER)
                    .header(CACHE_CONTROL_HEADER, cacheControl.toString())
                    .build()
        }
    }

    private fun initOfflineCacheInterceptor(networkManager: NetworkManager): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()

            if (!networkManager.isInternetAvailable()) {
                val cacheControl = CacheControl.Builder()
                        .maxStale(STALE_TIME, TimeUnit.DAYS)
                        .build()

                request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .build()
            }

            chain.proceed(request)
        }
    }

    companion object {
        private const val TAG = "AppModule"
        private const val CACHE_CONTROL_HEADER = "Cache-Control"
        private const val PRAGMA_HEADER = "Pragma"
        private const val ACCESS_CONTROL_ALLOW_ORIGIN_HEADER = "Access-Control-Allow-Origin"
        private const val VARY_HEADER = "Vary"
        private const val CACHE_SIZE = 10 * 1024 * 1024 // 10 MB
        private const val MAX_AGE = 10
        private const val STALE_TIME = 7
    }
}
