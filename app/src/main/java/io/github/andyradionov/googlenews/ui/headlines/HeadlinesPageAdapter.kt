package io.github.andyradionov.googlenews.ui.headlines

import android.content.Context
import android.content.res.Configuration
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import io.github.andyradionov.googlenews.R
import java.util.*

/**
 * @author Andrey Radionov
 */
class HeadlinesPageAdapter(private val context: Context, manager: FragmentManager)
    : FragmentStatePagerAdapter(manager) {

    private var titles: Array<String> = context.resources.getStringArray(R.array.headlines)
    private var headlines: Array<String> = getEnglishHeadlines()

    override fun getItem(position: Int) = HeadlinesPageFragment.newInstance(headlines[position])

    override fun getCount() = titles.size

    override fun getPageTitle(position: Int) = titles[position]

    private fun getEnglishHeadlines(): Array<String> {
        val configuration = getEnglishConfiguration()

        return context.createConfigurationContext(configuration)
                .resources.getStringArray(R.array.headlines)
    }

    private fun getEnglishConfiguration(): Configuration {
        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(Locale("en"))
        return configuration
    }
}