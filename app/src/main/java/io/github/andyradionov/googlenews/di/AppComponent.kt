package io.github.andyradionov.googlenews.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.github.andyradionov.googlenews.app.NewsApp
import io.github.andyradionov.googlenews.di.modules.ActivityBuilderModule
import io.github.andyradionov.googlenews.di.modules.AppModule
import io.github.andyradionov.googlenews.di.modules.FragmentBuilderModule
import javax.inject.Singleton

/**
 * @author Andrey Radionov
 */
@Singleton
@Component(modules = [
    AppModule::class,
    AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class,
    FragmentBuilderModule::class])
interface AppComponent : AndroidInjector<NewsApp> {

    override fun inject(newsApp: NewsApp)
}