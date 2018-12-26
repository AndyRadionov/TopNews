package io.github.andyradionov.topnews.utils

import android.app.Dialog
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