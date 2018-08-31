package andyradionov.github.io.googlenews.app

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import andyradionov.github.io.googlenews.di.AppComponent
import andyradionov.github.io.googlenews.di.DaggerAppComponent
import andyradionov.github.io.googlenews.di.NewsModule

/**
 * @author Andrey Radionov
 */
class App : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
                .builder()
                .newsModule(NewsModule(this))
                .build()
    }

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
