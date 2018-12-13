package io.github.andyradionov.googlenews.ui.dialogs

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.andyradionov.googlenews.R

/**
 * @author Andrey Radionov
 */

private const val ARG_IS_FAVOURITE = "is_favourite"

class NewsBottomSheetDialog : BottomSheetDialogFragment() {

    private var isFavourite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isFavourite = it.getBoolean(ARG_IS_FAVOURITE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
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
        return inflater.inflate(R.layout.fragment_dialog_bottom, container, false)
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    companion object {
        val TAG: String = NewsBottomSheetDialog::class.java.simpleName

        fun newInstance(isFavourite: Boolean = false) =
                NewsBottomSheetDialog().apply {
                    arguments = Bundle().apply {
                        putBoolean(ARG_IS_FAVOURITE, isFavourite)
                    }
                }
    }
}
