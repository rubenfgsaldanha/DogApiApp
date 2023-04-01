package com.example.dogapiapp.common.extensions

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

fun View.show() {
    if (parent == null) return
    visibility = VISIBLE
}

fun View.hide() {
    if (parent == null) return
    visibility = GONE
}