package io.github.andyradionov.googlenews.interactors

import io.github.andyradionov.googlenews.data.entities.Article
import io.github.andyradionov.googlenews.data.repositories.NewsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Andrey Radionov
 */
class NewsInteractor(private val newsRepository: NewsRepository) {

    fun fetchNews() = newsRepository.fetchNews()

    fun fetchNewsForHeadline(headline: String) = newsRepository.fetchNewsForHeadline(headline)

    fun searchNews(query: String) = newsRepository.searchNews(query)

    fun getFavourites() = newsRepository.getFavourites()

    fun addToFavourites(article: Article) = newsRepository.addToFavourites(article)

    fun removeFromFavourites(articleId: Int) = newsRepository.removeFromFavourites(articleId)
}
