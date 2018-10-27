package andyradionov.github.io.googlenews.app

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import andyradionov.github.io.googlenews.di.AppComponent
import andyradionov.github.io.googlenews.di.DaggerAppComponent
import andyradionov.github.io.googlenews.di.AppModule

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
                .appModule(AppModule(this))
                .build();
    }
}
