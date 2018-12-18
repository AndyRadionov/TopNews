package io.github.andyradionov.googlenews.ui.common.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import com.squareup.picasso.Picasso
import io.github.andyradionov.googlenews.R
import io.github.andyradionov.googlenews.data.entities.Article
import kotlinx.android.synthetic.main.item_article.view.*

/**
 * @author Andrey Radionov
 */
class NewsAdapterDelegate(private val clickListener: NewsAdapter.OnArticleClickListener) :
        AdapterDelegate<List<Article>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val cardView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_article, parent, false) as CardView
        return ArticleViewHolder(cardView)
    }

    override fun onBindViewHolder(items: List<Article>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        val articlesViewHolder = holder as ArticleViewHolder

        articlesViewHolder.bind(items[position])
    }

    override fun isForViewType(items: List<Article>, position: Int): Boolean {
        return items[position] is Article
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener {

        private var article: Article? = null

        init {
            itemView.setOnClickListener(this)
            itemView.iv_open_dialog.setOnClickListener(this)
        }

        fun bind(article: Article) {
            this.article = article
            itemView.tv_article_title.text = article.title

            Picasso.get()
                    .load(article.urlToImage)
                    .placeholder(R.drawable.error_placeholder)
                    .centerCrop()
                    .fit()
                    .into(itemView.iv_article_image)
        }

        override fun onClick(v: View) {
            article?.let {
                when(v) {
                    itemView.iv_open_dialog ->
                        clickListener.onOpenDialogClick(it)
                    else -> clickListener.onClick(it)
                }
            }

        }
    }
}
