package io.github.andyradionov.googlenews.data.repositories

import io.github.andyradionov.googlenews.data.datasource.local.NewsDao
import io.github.andyradionov.googlenews.data.datasource.server.NewsApi
import io.github.andyradionov.googlenews.data.entities.Article
import io.reactivex.Completable
import io.reactivex.Observable
import java.util.*

/**
 * @author Andrey Radionov
 */
class NewsRepository(private val newsApi: NewsApi,
                     private val newsDao: NewsDao) {

    fun fetchNews(): Observable<List<Article>> =
            newsApi.getTopNews(getLocale())
                    .map { it.articles }

    fun fetchNewsForHeadline(headline: String): Observable<List<Article>> =
            newsApi.getHeadlinesNews(getLocale(), headline)
                    .map { it.articles }

    fun searchNews(query: String): Observable<List<Article>> =
            newsApi.searchNews(query)
                    .map { it.articles }

    fun getFavourites() = newsDao.getFavouriteNews()

    fun addToFavourites(article: Article): Completable =
        Completable.fromAction {
            newsDao.addToFavourites(article)
        }


    fun removeFromFavourites(article: Article): Completable =
        Completable.fromAction {
            newsDao.removeFromFavouritesById(article)
        }

    private fun getLocale() = Locale.getDefault().country
}
