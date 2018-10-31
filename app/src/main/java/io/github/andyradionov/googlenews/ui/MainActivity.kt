package io.github.andyradionov.googlenews.ui

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import io.github.andyradionov.googlenews.R
import io.github.andyradionov.googlenews.ui.common.BaseFragment
import io.github.andyradionov.googlenews.ui.topnews.TopNewsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
//        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupBottomNavigation()
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

    private fun replaceFragment(fragment: BaseFragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
    }

    private fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }
}
