package io.github.andyradionov.googlenews.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.andyradionov.googlenews.ui.search.SearchDialogFragment
import io.github.andyradionov.googlenews.ui.topnews.TopNewsFragment

/**
 * @author Andrey Radionov
 */
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindTopNewsFragment(): TopNewsFragment

    @ContributesAndroidInjector
    abstract fun bindSearchDialogFragment(): SearchDialogFragment
}