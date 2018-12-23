package io.github.andyradionov.googlenews.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.github.andyradionov.googlenews.R
import io.github.andyradionov.googlenews.app.Screens
import io.github.andyradionov.googlenews.data.entities.Article
import io.github.andyradionov.googlenews.ui.common.BaseActivity
import io.github.andyradionov.googlenews.ui.menu.NewsBottomSheetDialog
import io.github.andyradionov.googlenews.ui.search.SearchDialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.Screen
import java.util.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = presenter
    private val selectedTabs = LinkedList<Int>()

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

    override fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showNotConnected() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBackPressed() {
        if (selectedTabs.size >= 2) {
            selectedTabs.pop()
            val tabId = selectedTabs.pop()
            bottom_navigation.selectedItemId = tabId
        } else {
            finish()
        }
    }

    override fun initBottomTab() {
        bottom_navigation.selectedItemId = R.id.action_top_news
    }

    private fun setupBottomNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            return@setOnNavigationItemSelectedListener when (item.itemId) {
                R.id.action_top_news -> {
                    //todo resources
                    changeTab(item, Screens.TopNewsScreen, "Top News")
                    true
                }
                R.id.action_headlines -> {
                    changeTab(item, Screens.HeadlinesScreen, "Headlines")
                    true
                }
                R.id.action_favourites -> {
                    changeTab(item, Screens.FavouritesScreen, "Favourites")
                    true
                }
                else -> false
            }
        }
    }

    private fun setupListeners() {
        iv_search.setOnClickListener {
            SearchDialogFragment().show(supportFragmentManager, SearchDialogFragment.TAG)
        }
    }

    private fun changeTab(item: MenuItem, screen: Screen, title: String) {
        appbar.setExpanded(true)
        selectedTabs.remove(item.itemId)
        selectedTabs.push(item.itemId)
        presenter.selectTab(screen)
        toolbar_title.text = title
    }
}
