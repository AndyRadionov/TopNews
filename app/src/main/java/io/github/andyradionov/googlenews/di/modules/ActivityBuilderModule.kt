package io.github.andyradionov.googlenews.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.andyradionov.googlenews.ui.main.MainActivity

/**
 * @author Andrey Radionov
 */
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}
