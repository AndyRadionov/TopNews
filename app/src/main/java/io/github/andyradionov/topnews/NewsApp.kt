package io.github.andyradionov.topnews

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.github.andyradionov.topnews.di.DaggerAppComponent

/**
 * @author Andrey Radionov
 */
class NewsApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerAppComponent
                .builder()
                .app(this)
                .build()
}
