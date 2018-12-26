package io.github.andyradionov.topnews.ui.headlines

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.github.andyradionov.topnews.ui.common.BaseNewsFragment
import io.github.andyradionov.topnews.ui.common.BaseNewsView
import kotlinx.android.synthetic.main.news_content_layout.*
import javax.inject.Inject

private const val ARG_PAGE_HEADLINE = "page_headline"

class HeadlinesPageFragment : BaseNewsFragment(), BaseNewsView {

    @Inject
    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: HeadlinesPresenter

    private lateinit var pageHeadline: String

    @ProvidePresenter
    fun providePresenter(): HeadlinesPresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pageHeadline = it.getString(ARG_PAGE_HEADLINE) as String
        }
    }

    override fun setUpSwipeRefresh() {
        swipe_container.setOnRefreshListener {
            presenter.fetchNewsForHeadline(pageHeadline)
        }
    }

    override fun loadNews() {
        showLoading()
        presenter.fetchNewsForHeadline(pageHeadline)
    }

    companion object {
        fun newInstance(pageHeadline: String) =
                HeadlinesPageFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PAGE_HEADLINE, pageHeadline)
                    }
                }
    }
}
