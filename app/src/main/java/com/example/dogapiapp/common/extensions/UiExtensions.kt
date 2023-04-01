package com.example.dogapiapp.common.extensions

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.annotation.StringRes

fun View.show() {
    if (parent == null) return
    visibility = VISIBLE
}

fun View.hide() {
    if (parent == null) return
    visibility = GONE
}

fun TextView.setText(textToShow: String?, @StringRes stringId: Int) {
    if (textToShow.isNullOrEmpty()) {
        hide()
    } else {
        text = context.resources.getString(stringId).plus(textToShow)
        show()
    }
}