package io.github.andyradionov.googlenews.ui.main

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.github.andyradionov.googlenews.R
import io.github.andyradionov.googlenews.app.Screens
import io.github.andyradionov.googlenews.data.entities.Article
import io.github.andyradionov.googlenews.ui.common.BaseActivity
import io.github.andyradionov.googlenews.ui.dialogs.NewsBottomSheetDialog
import io.github.andyradionov.googlenews.ui.search.SearchDialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.Screen
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setupBottomNavigation()
        setupListeners()
    }

    override fun showBottomSheet(article: Article) {
        NewsBottomSheetDialog
                .newInstance(article)
                .show(supportFragmentManager, NewsBottomSheetDialog.TAG)
    }

    override fun showNotConnected() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setupBottomNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            return@setOnNavigationItemSelectedListener when (item.itemId) {
                R.id.action_top_news -> {
                    //todo resources
                    changeTab(Screens.TopNewsScreen, "Top News")
                    true
                }
                R.id.action_headlines -> {
                    changeTab(Screens.HeadlinesScreen, "Headlines")
                    true
                }
                R.id.action_favorites -> {
                    changeTab(Screens.FavouritesScreen, "Favourites")
                    true
                }
                else -> false
            }
        }
        bottom_navigation.selectedItemId = R.id.action_top_news
    }

    private fun setupListeners() {
        iv_search.setOnClickListener {
            SearchDialogFragment().show(supportFragmentManager, SearchDialogFragment.TAG)
        }
    }

    private fun changeTab(screen: Screen, title: String) {
        presenter.selectTab(screen)
        toolbar_title.text = title
    }
}
