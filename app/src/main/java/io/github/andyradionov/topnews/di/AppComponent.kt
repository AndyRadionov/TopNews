package io.github.andyradionov.topnews.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.github.andyradionov.topnews.NewsApp
import io.github.andyradionov.topnews.di.modules.*
import javax.inject.Singleton

/**
 * @author Andrey Radionov
 */
@Singleton
@Component(modules = [
    AppModule::class,
    NavigationModule::class,
    NetworkModule::class,
    PersistenceModule::class,
    AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class,
    FragmentBuilderModule::class])
interface AppComponent : AndroidInjector<NewsApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(app: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}