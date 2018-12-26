package io.github.andyradionov.topnews.ui.favourites

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.github.andyradionov.topnews.ui.common.BaseNewsFragment
import io.github.andyradionov.topnews.ui.common.BaseNewsView
import kotlinx.android.synthetic.main.news_content_layout.*
import javax.inject.Inject

class FavouritesFragment : BaseNewsFragment(), BaseNewsView {

    @Inject
    @InjectPresenter
    lateinit var presenter: FavouritesPresenter

    @ProvidePresenter
    fun providePresenter(): FavouritesPresenter = presenter

    override fun setUpSwipeRefresh() {
        swipe_container.setOnRefreshListener {
            presenter.loadFavourites()
        }
    }

    override fun loadNews() {
        showLoading()
        presenter.loadFavourites()
    }
}
