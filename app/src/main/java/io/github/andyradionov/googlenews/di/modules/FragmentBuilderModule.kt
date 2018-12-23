package io.github.andyradionov.googlenews.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.andyradionov.googlenews.ui.menu.NewsBottomSheetDialog
import io.github.andyradionov.googlenews.ui.favourites.FavouritesFragment
import io.github.andyradionov.googlenews.ui.headlines.HeadlinesFragment
import io.github.andyradionov.googlenews.ui.headlines.HeadlinesPageFragment
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
    abstract fun bindHeadlinesFragment(): HeadlinesFragment

    @ContributesAndroidInjector
    abstract fun bindHeadlinesPageFragment(): HeadlinesPageFragment

    @ContributesAndroidInjector
    abstract fun bindFavouritesFragment(): FavouritesFragment

    @ContributesAndroidInjector
    abstract fun bindSearchDialogFragment(): SearchDialogFragment

    @ContributesAndroidInjector
    abstract fun bindBottomSheetDialog(): NewsBottomSheetDialog
}
