package andyradionov.github.io.googlenews.data

import andyradionov.github.io.googlenews.app.App
import andyradionov.github.io.googlenews.news.NewsContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
class NewsStore {

    @Inject lateinit var mNewsApi: NewsApi

    init {
        App.appComponent.inject(this)
    }

    private var cacheQuery: String = ""
    private var cache: List<Article> = emptyList()

    fun getTopNews(presenter: NewsContract.Presenter) {

        if (cacheQuery.isEmpty() && !cache.isEmpty()){
            presenter.showNews(cache)
            return
        }

        mNewsApi.getTopNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError({ presenter.showError() })
                .map { it.articles }
                .subscribe({
                    if (it.isEmpty()) {
                        presenter.showError()
                    } else {
                        cacheQuery = ""
                        cache = it
                        presenter.showNews(it)
                    }
                }, { presenter.showError() })
    }

    fun searchNews(query: String, presenter: NewsContract.Presenter) {

        if (cacheQuery == query && !cache.isEmpty()){
            presenter.showNews(cache)
            return
        }

        mNewsApi.searchNews(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError({ presenter.showError() })
                .map { it.articles }
                .subscribe({
                    if (it.isEmpty()) {
                        presenter.showError()
                    } else {
                        cacheQuery = query
                        cache = it
                        presenter.showNews(it)
                    }
                }, { presenter.showError() })
    }
}