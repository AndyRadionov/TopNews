package andyradionov.github.io.googlenews.di

import andyradionov.github.io.googlenews.ui.news.NewsActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * @author Andrey Radionov
 */
@Subcomponent
interface NewsActivitySubcomponent : AndroidInjector<NewsActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<NewsActivity>()
}