package io.github.andyradionov.topnews.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import io.github.andyradionov.topnews.data.datasource.local.AppDatabase
import io.github.andyradionov.topnews.data.datasource.local.NewsDao
import javax.inject.Singleton

/**
 * @author Andrey Radionov
 */
@Module
class PersistenceModule {

    @Provides
    @Singleton
    fun provideNewsDao(database: AppDatabase): NewsDao = database.newsDao

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase =
            Room.databaseBuilder(app, AppDatabase::class.java, "google_news_db")
                    .build()
}