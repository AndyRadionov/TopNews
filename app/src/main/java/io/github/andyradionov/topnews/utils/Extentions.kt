package io.github.andyradionov.topnews.utils

import android.app.Dialog
import android.view.View
import android.widget.TextView

/**
 * @author Andrey Radionov
 */

fun TextView.setDialogActionListener(dialog: Dialog, action: () -> Unit) {
    this.setOnClickListener {
        action()
        dialog.dismiss()
    }
}

fun View.getVisibility(isVisible: Boolean) = if (isVisible) View.VISIBLE else View.INVISIBLE
