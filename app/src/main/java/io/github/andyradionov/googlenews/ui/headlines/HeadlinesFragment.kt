package io.github.andyradionov.googlenews.ui.headlines

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.github.andyradionov.googlenews.R
import io.github.andyradionov.googlenews.data.entities.Article
import io.github.andyradionov.googlenews.ui.common.BaseFragment
import io.github.andyradionov.googlenews.ui.topnews.NewsAdapter
import io.github.andyradionov.googlenews.ui.topnews.TopNewsPresenter
import javax.inject.Inject


class HeadlinesFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_headlines, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = view.findViewById<ViewPager>(R.id.viewpager)
        viewPager.adapter = HeadlinesPageAdapter(context!!, childFragmentManager)
        viewPager.offscreenPageLimit = 0
        val tabLayout = view.findViewById<TabLayout>(R.id.tabs)
        tabLayout.setupWithViewPager(viewPager)
    }
}
