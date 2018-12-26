package io.github.andyradionov.topnews.data.datasource.server

import io.github.andyradionov.topnews.BuildConfig.*
import io.github.andyradionov.topnews.data.entities.NewsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Andrey Radionov
 */
private const val TOP_NEWS_REQUEST = "top-headlines?pageSize=$PAGE_SIZE"
private const val SEARCH_NEWS_REQUEST = "everything?sortBy=$SORT_BY&pageSize=$SEARCH_SIZE"

interface NewsApi {

    @GET(TOP_NEWS_REQUEST)
    fun getTopNews(@Query("country") country: String): Observable<NewsResponse>

    @GET(TOP_NEWS_REQUEST)
    fun getHeadlinesNews(@Query("country") country: String,
                         @Query("category") category: String): Observable<NewsResponse>

    @GET(SEARCH_NEWS_REQUEST)
    fun searchNews(@Query("q") query: String): Observable<NewsResponse>
}
