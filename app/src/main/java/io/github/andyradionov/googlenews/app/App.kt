package io.github.andyradionov.googlenews.app

import android.app.Application
import andyradionov.github.io.googlenews.di.DaggerAppComponent
import io.github.andyradionov.googlenews.di.AppComponent
import io.github.andyradionov.googlenews.di.AppModule

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
