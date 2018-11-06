package io.github.andyradionov.googlenews.data.repositories

import io.github.andyradionov.googlenews.data.datasource.remote.NewsApi
import io.github.andyradionov.googlenews.data.entities.Article
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Andrey Radionov
 */
class NewsRepository(private val newsApi: NewsApi) {

    fun fetchNews(): Observable<List<Article>> =
            newsApi.getTopNews()
                    .map { it.articles }

    fun fetchNewsForHeadline(headline: String): Observable<List<Article>> =
            newsApi.getHeadlinesNews(headline)
                    .map { it.articles }

    fun searchNews(query: String): Observable<List<Article>> =
            newsApi.searchNews(query)
                    .map { it.articles }

}
