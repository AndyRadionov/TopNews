package io.github.andyradionov.googlenews.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.andyradionov.googlenews.R
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog

/**
 * @author Andrey Radionov
 */
const val DIALOG_TYPE_REGULAR = "regular_dialog"
const val DIALOG_TYPE_FAVOURITE = "favourite_dialog"
private const val ARG_DIALOG_TYPE = "dialog_type"

class NewsBottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var dialogType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dialogType = it.getString(ARG_DIALOG_TYPE)
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

        fun newInstance(dialogType: String = DIALOG_TYPE_REGULAR) =
                NewsBottomSheetDialog().apply {
                    arguments = Bundle().apply {
                        putString(ARG_DIALOG_TYPE, dialogType)
                    }
                }
    }
}
