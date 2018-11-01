package io.github.andyradionov.googlenews.app

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.github.andyradionov.googlenews.di.DaggerAppComponent
import io.github.andyradionov.googlenews.di.modules.NetworkModule

/**
 * @author Andrey Radionov
 */
class NewsApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerAppComponent
                .builder()
                .networkModule(NetworkModule(this))
                .build()
}
