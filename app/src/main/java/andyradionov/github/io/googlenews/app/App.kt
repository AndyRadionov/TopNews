package andyradionov.github.io.googlenews.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import andyradionov.github.io.googlenews.di.AppComponent
import andyradionov.github.io.googlenews.di.DaggerAppComponent
import andyradionov.github.io.googlenews.di.NewsModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
class App : Application(), HasActivityInjector {

    @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
                .builder()
                .newsModule(NewsModule(this))
                .build()
                .inject(this)
    }

    override fun activityInjector() = dispatchingActivityInjector

    fun isInternetAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        return connectivityManager?.let {
            with(connectivityManager) {
                activeNetworkInfo != null
                        && activeNetworkInfo.isAvailable
                        && activeNetworkInfo.isConnected
            }
        }
    }
}
