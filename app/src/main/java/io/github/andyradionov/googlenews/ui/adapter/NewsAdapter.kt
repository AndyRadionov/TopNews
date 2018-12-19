package io.github.andyradionov.googlenews.ui.adapter

import android.support.v7.widget.RecyclerView
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.hannesdorfmann.adapterdelegates3.AsyncListDifferDelegationAdapter
import io.github.andyradionov.googlenews.data.entities.Article
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */
class NewsAdapter @Inject constructor(
        private val presenter: NewsAdapterPresenter) :
        AsyncListDifferDelegationAdapter<Article>(ArticlesDiffCallback()), MvpView {

    @ProvidePresenter
    fun providePresenter(): NewsAdapterPresenter = presenter

    interface OnArticleClickListener {
        fun onClick(article: Article)
        fun onOpenDialogClick(article: Article)
    }

    init {
        val clickListener = initClickListener()
        delegatesManager.addDelegate(NewsAdapterDelegate(clickListener))
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        presenter.attachView(this)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        presenter.detachView(this)
    }

    private fun initClickListener(): OnArticleClickListener {
        return object : OnArticleClickListener {
            override fun onClick(article: Article) {
                presenter.openDetailsScreen(article)
            }

            override fun onOpenDialogClick(article: Article) {
                presenter.showBottomDialog(article)
            }
        }
    }
}
