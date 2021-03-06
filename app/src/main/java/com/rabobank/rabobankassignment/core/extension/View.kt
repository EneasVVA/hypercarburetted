package com.rabobank.rabobankassignment.core.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun <T : View> ViewGroup.inflate(@LayoutRes layoutRes: Int): T =
    LayoutInflater.from(context).inflate(layoutRes, this, false) as T

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}