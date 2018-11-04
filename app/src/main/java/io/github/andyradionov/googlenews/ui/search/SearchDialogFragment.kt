package io.github.andyradionov.googlenews.ui.search

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatDialogFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.github.andyradionov.googlenews.R
import javax.inject.Inject

class SearchDialogFragment : MvpAppCompatDialogFragment(), SearchView {

//    @Inject
//    @InjectPresenter
//    lateinit var searchPresenter: SearchPresenter
//
//    @ProvidePresenter
//    fun providePresenter() = searchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_dialog, container, false)
    }



//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.main, menu)
//
//        val searchAction = menu.findItem(R.id.action_search)
//
//        val searchView = searchAction.actionView as SearchView
//        searchView.maxWidth = Integer.MAX_VALUE
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                loadNews(query)
//                searchView.clearFocus()
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                return false
//            }
//        })
//
//        searchAction.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
//            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
//                searchView.imeOptions = EditorInfo.IME_FLAG_FORCE_ASCII
//                return true
//            }
//
//            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
//                loadNews(EMPTY_QUERY)
//                invalidateOptionsMenu()
//                return true
//            }
//        })
//
//        if (!query.isEmpty()) {
//            searchAction.expandActionView()
//            searchView.setQuery(query, true)
//            searchView.clearFocus()
//        }
//
//        return true
//    }

    companion object {
        val TAG = SearchDialogFragment::class.java.simpleName
    }
}
