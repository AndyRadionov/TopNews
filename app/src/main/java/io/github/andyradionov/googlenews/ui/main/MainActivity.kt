package io.github.andyradionov.googlenews.ui.main

import android.os.Bundle
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.github.andyradionov.googlenews.R
import io.github.andyradionov.googlenews.app.Screens
import io.github.andyradionov.googlenews.ui.common.BaseActivity
import io.github.andyradionov.googlenews.ui.search.SearchDialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MvpView {

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

    private fun setupBottomNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            return@setOnNavigationItemSelectedListener when (item.itemId) {
                R.id.action_top_news -> {
                    presenter.selectTab(Screens.TopNewsScreen)
                    setToolbarTitle("Top News")
                    true
                }
                R.id.action_headlines -> {
                    presenter.selectTab(Screens.HeadlinesScreen)
                    setToolbarTitle("Headlines")
                    true
                }
                R.id.action_favorites -> {
                    presenter.selectTab(Screens.FavouritesScreen)
                    setToolbarTitle("Favourites")
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

    private fun setToolbarTitle(title: String) {
        toolbar_title.text = title
    }
}
