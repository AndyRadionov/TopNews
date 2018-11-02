package io.github.andyradionov.googlenews.di.modules

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

/**
 * @author Andrey Radionov
 */
@Module
class NavigationModule {

    @Provides
    @Singleton
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    @Singleton
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder
            = cicerone.navigatorHolder

    @Provides
    @Singleton
    fun provideCiceron(): Cicerone<Router> = Cicerone.create()
}
