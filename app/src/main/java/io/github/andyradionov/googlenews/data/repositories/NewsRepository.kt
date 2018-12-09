package io.github.andyradionov.googlenews.data.repositories

import io.github.andyradionov.googlenews.data.datasource.local.NewsDao
import io.github.andyradionov.googlenews.data.datasource.remote.NewsApi
import io.github.andyradionov.googlenews.data.entities.Article
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * @author Andrey Radionov
 */
class NewsRepository(private val newsApi: NewsApi,
                     private val newsDao: NewsDao) {

    fun fetchNews(): Observable<List<Article>> =
            newsApi.getTopNews()
                    .map { it.articles }

    fun fetchNewsForHeadline(headline: String): Observable<List<Article>> =
            newsApi.getHeadlinesNews(headline)
                    .map { it.articles }

    fun searchNews(query: String): Observable<List<Article>> =
            newsApi.searchNews(query)
                    .map { it.articles }

    fun getFavourites() = newsDao.getFavouriteNews()

    fun addToFavourites(article: Article): Completable =
        Completable.fromAction {
            newsDao.addToFavourites(article)
        }


    fun removeFromFavourites(articleId: Int): Completable =
        Completable.fromAction {
            newsDao.removeFromFavouritesById(articleId)
        }
}
