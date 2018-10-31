package andyradionov.github.io.googlenews.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import andyradionov.github.io.googlenews.R
import andyradionov.github.io.googlenews.ui.common.BaseFragment
import andyradionov.github.io.googlenews.ui.news.TopNewsFragment
import com.arellomobile.mvp.MvpAppCompatActivity
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
