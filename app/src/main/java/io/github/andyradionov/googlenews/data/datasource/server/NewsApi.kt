package io.github.andyradionov.googlenews.data.datasource.server

import io.github.andyradionov.googlenews.BuildConfig.PAGE_SIZE
import io.github.andyradionov.googlenews.BuildConfig.SORT_BY
import io.github.andyradionov.googlenews.data.entities.NewsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Andrey Radionov
 */
private const val TOP_NEWS_REQUEST = "top-headlines?pageSize=$PAGE_SIZE"
private const val SEARCH_NEWS_REQUEST = "everything?sortBy=$SORT_BY&pageSize=100"//todo

interface NewsApi {

    @GET(TOP_NEWS_REQUEST)
    fun getTopNews(@Query("country") country: String): Observable<NewsResponse>

    @GET(TOP_NEWS_REQUEST)
    fun getHeadlinesNews(@Query("country") country: String,
                         @Query("category") category: String): Observable<NewsResponse>

    @GET(SEARCH_NEWS_REQUEST)
    fun searchNews(@Query("q") query: String): Observable<NewsResponse>
}
