package io.github.andyradionov.googlenews.ui.dialogs

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.android.support.AndroidSupportInjection
import io.github.andyradionov.googlenews.R
import io.github.andyradionov.googlenews.data.entities.Article
import kotlinx.android.synthetic.main.fragment_dialog_bottom.view.*
import javax.inject.Inject

/**
 * @author Andrey Radionov
 */

private const val ARG_ARTICLE = "article"

class NewsBottomSheetDialog : BottomSheetDialogFragment(), MvpView {

    @Inject
    @InjectPresenter
    lateinit var presenter: NewsBottomSheetPresenter
    private lateinit var article: Article

    @ProvidePresenter
    fun providePresenter(): NewsBottomSheetPresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            article = it.getParcelable(ARG_ARTICLE) as Article
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        initDialog()
        val view = inflater.inflate(R.layout.fragment_dialog_bottom, container, false)
        initView(view)
        setClickListeners(view)
        return view
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    private fun initDialog() {
        dialog.setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog
            val bottomSheet = d
                    .findViewById<View>(android.support.design.R.id.design_bottom_sheet)
            bottomSheet?.let {
                BottomSheetBehavior
                        .from(bottomSheet)
                        .state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }

    private fun initView(view: View) {
        if (article.isFavourite) {
            view.tv_add_favourite_action.visibility = View.GONE
            view.tv_remove_favourite_action.visibility = View.VISIBLE
        }
    }

    private fun setClickListeners(view: View) {
        view.tv_copy_link_action.setOnClickListener {  }
        view.tv_add_favourite_action.setOnClickListener {
            presenter.addToFavourites(article)
            dismiss()
        }
        view.tv_remove_favourite_action.setOnClickListener {
            presenter.removeFromFavourites(article)
            dismiss()
        }
        view.tv_open_action.setOnClickListener {  }
        view.tv_read_action.setOnClickListener {  }
        view.tv_share_action.setOnClickListener {
            presenter.shareArticle(article)
            dismiss()
        }
    }

    companion object {
        val TAG: String = NewsBottomSheetDialog::class.java.simpleName

        fun newInstance(article: Article) =
                NewsBottomSheetDialog().apply {
                    arguments = Bundle().apply {
                        putParcelable(ARG_ARTICLE, article)
                    }
                }
    }
}
