package io.github.andyradionov.googlenews.ui.adapter

import android.net.Uri
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import com.squareup.picasso.Picasso
import io.github.andyradionov.googlenews.BuildConfig.ICONS_URL
import io.github.andyradionov.googlenews.R
import io.github.andyradionov.googlenews.data.entities.Article
import kotlinx.android.synthetic.main.item_article.view.*
import org.ocpsoft.prettytime.PrettyTime

/**
 * @author Andrey Radionov
 */
class NewsAdapterDelegate(private val clickListener: NewsAdapter.OnArticleClickListener) :
        AdapterDelegate<List<Article>>() {

    val prettyTime = PrettyTime()

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

            val baseUrl = Uri.parse(article.url).host
            val imgUrl = if (TextUtils.isEmpty(article.urlToImage))
                String.format(ICONS_URL, baseUrl) else article.urlToImage
            Picasso.get()
                    .load(imgUrl)
                    .placeholder(R.drawable.error_placeholder)
                    .into(itemView.iv_article_image)

            itemView.tv_article_author.text = baseUrl
            itemView.tv_article_title.text = article.title.substringBeforeLast("-")
            itemView.tv_article_date.text = prettyTime.format(article.publishedAt)
        }

        override fun onClick(v: View) {
            article?.let {
                when (v) {
                    itemView.iv_open_dialog -> clickListener.onOpenDialogClick(it)
                    else -> clickListener.onClick(it)
                }
            }

        }
    }
}
