package io.github.andyradionov.googlenews.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.github.andyradionov.googlenews.app.NewsApp
import io.github.andyradionov.googlenews.di.modules.*
import javax.inject.Singleton

/**
 * @author Andrey Radionov
 */
@Singleton
@Component(modules = [
    MainModule::class,
    NavigationModule::class,
    NetworkModule::class,
    AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class,
    FragmentBuilderModule::class])
interface AppComponent : AndroidInjector<NewsApp> {

    override fun inject(newsApp: NewsApp)
}