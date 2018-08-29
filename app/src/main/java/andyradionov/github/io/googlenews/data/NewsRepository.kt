package andyradionov.github.io.googlenews.data

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author Andrey Radionov
 */
open class NewsRepository(private val newsApi: NewsApi) {

    private var mSubscription: Disposable? = null
    private var cacheQuery: String = ""
    private var cache: List<Article> = emptyList()

    open fun fetchNews(query: String = "", callback: NewsCallback) {

        unsubscribe()

        if (isCached(query)) {
            callback.onSuccessLoading(cache)
            return
        }

        val newsObservable =
                if (query.isEmpty()) {
                    newsApi.getTopNews()
                } else {
                    newsApi.searchNews(query)
                }

        mSubscription = newsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError({ callback.onErrorLoading() })
                .map { it.articles }
                .subscribe({
                    if (it.isEmpty()) {
                        callback.onErrorLoading()
                    } else {
                        cacheQuery = query
                        cache = it
                        callback.onSuccessLoading(it)
                    }
                }, { callback.onErrorLoading() })
    }

    fun unsubscribe() {
        if (mSubscription?.isDisposed == true) {
            mSubscription?.dispose();
            mSubscription = null;
        }
    }

    private fun isCached(query: String): Boolean {
        return query == cacheQuery && !cache.isEmpty()
    }
}