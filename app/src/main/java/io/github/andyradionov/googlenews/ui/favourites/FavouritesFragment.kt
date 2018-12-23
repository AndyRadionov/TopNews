package io.github.andyradionov.googlenews.ui.favourites

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.github.andyradionov.googlenews.ui.common.BaseNewsFragment
import kotlinx.android.synthetic.main.news_content_layout.*
import javax.inject.Inject

class FavouritesFragment : BaseNewsFragment(), FavouritesView {

    @Inject
    @InjectPresenter
    lateinit var presenter: FavouritesPresenter

    @ProvidePresenter
    fun providePresenter(): FavouritesPresenter = presenter


    override fun onFavouriteRemove(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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
