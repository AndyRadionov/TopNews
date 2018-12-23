package io.github.andyradionov.googlenews.ui.topnews

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.github.andyradionov.googlenews.ui.common.BaseNewsFragment
import io.github.andyradionov.googlenews.ui.common.BaseNewsView
import kotlinx.android.synthetic.main.news_content_layout.*
import javax.inject.Inject

class TopNewsFragment : BaseNewsFragment(), BaseNewsView {

    @Inject
    @InjectPresenter
    lateinit var presenter: TopNewsPresenter

    @ProvidePresenter
    fun providePresenter(): TopNewsPresenter {
        return presenter
    }

    override fun setUpSwipeRefresh() {
        swipe_container.setOnRefreshListener {
            presenter.fetchNews()
        }
    }

    override fun loadNews() {
        showLoading()
        presenter.fetchNews()
    }
}
