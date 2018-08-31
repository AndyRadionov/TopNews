package andyradionov.github.io.googlenews.data

import andyradionov.github.io.googlenews.data.entities.Article
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Andrey Radionov
 */
open class NewsRepository(private val newsApi: NewsApi) {

    open fun fetchNews(query: String): Observable<List<Article>> {

        val newsObservable =
                if (query.isEmpty()) {
                    newsApi.getTopNews()
                } else {
                    newsApi.searchNews(query)
                }

        return newsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it.articles }
    }
}
