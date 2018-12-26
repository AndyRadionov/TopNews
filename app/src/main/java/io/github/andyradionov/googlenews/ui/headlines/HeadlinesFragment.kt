package io.github.andyradionov.googlenews.ui.headlines

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import io.github.andyradionov.googlenews.R
import kotlinx.android.synthetic.main.activity_main.*

class HeadlinesFragment : MvpAppCompatFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_headlines, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = view.findViewById<ViewPager>(R.id.viewpager)
        viewPager.adapter = HeadlinesPageAdapter(context!!, childFragmentManager)

        activity?.tabs?.setupWithViewPager(viewPager)
        activity?.tabs?.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.tabs?.visibility = View.GONE
    }
}
