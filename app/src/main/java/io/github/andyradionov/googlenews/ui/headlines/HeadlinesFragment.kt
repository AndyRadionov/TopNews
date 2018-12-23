package io.github.andyradionov.googlenews.ui.headlines

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import io.github.andyradionov.googlenews.R

class HeadlinesFragment : MvpAppCompatFragment() {

    private lateinit var tabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_headlines, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val activity = context as Activity
        tabLayout = activity.findViewById(R.id.tabs)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = view.findViewById<ViewPager>(R.id.viewpager)
        viewPager.adapter = HeadlinesPageAdapter(context!!, childFragmentManager)

        tabLayout.setupWithViewPager(viewPager)
        tabLayout.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tabLayout.visibility = View.GONE
    }
}
