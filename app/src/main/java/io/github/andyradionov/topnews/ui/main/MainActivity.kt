package io.github.andyradionov.topnews.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.github.andyradionov.topnews.R
import io.github.andyradionov.topnews.Screens
import io.github.andyradionov.topnews.data.entities.Article
import io.github.andyradionov.topnews.ui.common.BaseActivity
import io.github.andyradionov.topnews.ui.menu.NewsBottomSheetDialog
import io.github.andyradionov.topnews.ui.search.SearchDialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.Screen
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

private const val EXTRA_TABS_ID = "tabs_id"
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
        restoreState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putIntegerArrayList(EXTRA_TABS_ID, ArrayList(selectedTabs))
        super.onSaveInstanceState(outState)
    }

    override fun showBottomSheet(article: Article) {
        NewsBottomSheetDialog
                .newInstance(article)
                .show(supportFragmentManager, NewsBottomSheetDialog.TAG)
    }

    override fun showMessage(msgId: Int) {
        Toast.makeText(this, msgId, Toast.LENGTH_SHORT).show()
    }

    override fun showNotConnected() {
        Toast.makeText(this, getString(R.string.error_no_internet), Toast.LENGTH_SHORT).show()
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
                    changeTab(item, Screens.TopNewsScreen, getString(R.string.top_news))
                    true
                }
                R.id.action_headlines -> {
                    changeTab(item, Screens.HeadlinesScreen, getString(R.string.headlines))
                    true
                }
                R.id.action_favourites -> {
                    changeTab(item, Screens.FavouritesScreen, getString(R.string.favourites))
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

    private fun restoreState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            selectedTabs.addAll(savedInstanceState.getIntegerArrayList(EXTRA_TABS_ID))
            val tabId = selectedTabs.pop()
            bottom_navigation.selectedItemId = tabId
        }
    }
}
