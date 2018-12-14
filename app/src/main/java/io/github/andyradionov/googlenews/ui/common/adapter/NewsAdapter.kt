package io.github.andyradionov.googlenews.ui.common.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import io.github.andyradionov.googlenews.R
import io.github.andyradionov.googlenews.data.entities.Article
import kotlinx.android.synthetic.main.item_article.view.*

/**
 * @author Andrey Radionov
 */
class NewsAdapter(private val clickListener: OnArticleClickListener) :
        ListAdapter<Article, NewsAdapter.ArticleViewHolder>(ArticlesDiffCallback()) {

    interface OnArticleClickListener {
        fun onClick(articleUrl: String)
        fun onOpenDialogClick(articleUrl: String, isFavourite: Boolean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val cardView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_article, parent, false) as CardView
        return ArticleViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
            View.OnClickListener {

        fun bind(position: Int) {
            itemView.setOnClickListener(this)
            itemView.iv_open_dialog.setOnClickListener(this)
            val article = getItem(position)

            itemView.tv_article_title.text = article.title

            Picasso.get()
                    .load(article.urlToImage)
                    .placeholder(R.drawable.error_placeholder)
                    .centerCrop()
                    .fit()
                    .into(itemView.iv_article_image)
        }

        override fun onClick(v: View) {
            val article = getItem(adapterPosition)
            when(v) {
                itemView.iv_open_dialog ->
                    clickListener.onOpenDialogClick(article.url, article.isFavourite)
                else -> clickListener.onClick(article.url)
            }
        }
    }
}
