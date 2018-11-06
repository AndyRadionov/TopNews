package io.github.andyradionov.googlenews.data.repositories

import io.github.andyradionov.googlenews.data.datasource.remote.NewsApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Andrey Radionov
 */
class NewsRepository(private val newsApi: NewsApi) {

    fun fetchNews() =
            newsApi.getTopNews()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { it.articles }

    fun fetchNewsForHeadline(headline: String) =
            newsApi.getHeadlinesNews(headline)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { it.articles }

    fun searchNews(query: String) =
            newsApi.searchNews(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { it.articles }

}
