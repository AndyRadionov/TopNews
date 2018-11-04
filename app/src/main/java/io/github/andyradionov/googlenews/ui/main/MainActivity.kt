package io.github.andyradionov.googlenews.ui.main

import android.os.Bundle
import io.github.andyradionov.googlenews.R
import io.github.andyradionov.googlenews.ui.common.BaseActivity
import io.github.andyradionov.googlenews.ui.common.BaseFragment
import io.github.andyradionov.googlenews.ui.search.SearchDialogFragment
import io.github.andyradionov.googlenews.ui.topnews.TopNewsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

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
                    replaceFragment(TopNewsFragment())
                    setToolbarTitle("Top News")
                    true
                }
                else -> false
            }
        }
        bottom_navigation.selectedItemId = R.id.action_top_news
    }

    private fun setupListeners() {
        action_search.setOnClickListener {
            SearchDialogFragment().show(supportFragmentManager, SearchDialogFragment.TAG)
        }
    }

    private fun replaceFragment(fragment: BaseFragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
    }

    private fun setToolbarTitle(title: String) {
        toolbar_title.text = title
    }
}
