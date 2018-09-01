package andyradionov.github.io.googlenews.di

import android.app.Activity
import andyradionov.github.io.googlenews.ui.news.NewsActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap


/**
 * @author Andrey Radionov
 */
@Module(subcomponents = [NewsActivitySubcomponent::class])
abstract class NewsActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(NewsActivity::class)
    abstract fun bindNewsActivityInjectorFactory(builder: NewsActivitySubcomponent.Builder): AndroidInjector.Factory<out Activity>
}