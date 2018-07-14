package andyradionov.github.io.googlenews.app

import android.app.Application
import andyradionov.github.io.googlenews.app.di.AppComponent
import andyradionov.github.io.googlenews.app.di.DaggerAppComponent
import andyradionov.github.io.googlenews.app.di.NewsModule

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
                .newsModule(NewsModule())
                .build()
    }
}