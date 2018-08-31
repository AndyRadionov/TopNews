package andyradionov.github.io.googlenews.ui.news

import andyradionov.github.io.googlenews.data.entities.Article
import andyradionov.github.io.googlenews.ui.news.NewsActivity.Companion.EMPTY_QUERY
import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState

/**
 * @author Andrey Radionov
 */
class NewsViewState : ViewState<NewsContract.View> {
    companion object {
        private const val STATE_DO_NOTHING = 0
        private const val STATE_SHOW_DATA = 1
        private const val STATE_SHOW_LOADING = 2
        private const val STATE_SHOW_ERROR = 3
    }

    private var state = STATE_DO_NOTHING

    private var query: String = EMPTY_QUERY
    private var data: List<Article>? = null
    private var listPosition = 0

    fun getQuery() = query

    fun setQuery(query: String) {
        this.query = query
    }

    fun setData(data: List<Article>) {
        state = STATE_SHOW_DATA
        this.data = data
    }

    fun setListPosition(position: Int) {
        listPosition = position
    }

    fun getListPosition() = listPosition

    fun setShowLoading() {
        state = STATE_SHOW_LOADING
    }

    fun setShowError() {
        state = STATE_SHOW_ERROR
    }

    override fun apply(view: NewsContract.View, retained: Boolean) {
        when (state) {
            STATE_SHOW_DATA -> view.showNews(data!!)
            STATE_SHOW_LOADING -> view.showLoading()
            STATE_SHOW_ERROR -> {
                data = null
                view.showError()
            }
        }
    }
}