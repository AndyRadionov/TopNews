package io.github.andyradionov.googlenews.ui.common

import android.content.Context
import com.arellomobile.mvp.MvpAppCompatFragment
import dagger.android.support.AndroidSupportInjection

/**
 * @author Andrey Radionov
 */
abstract class BaseFragment : MvpAppCompatFragment() {

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}