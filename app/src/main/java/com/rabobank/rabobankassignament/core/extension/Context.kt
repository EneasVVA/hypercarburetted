package com.rabobank.rabobankassignament.core.extension

import android.content.Context
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog


fun Context.showErrorDialog(@StringRes errorMessageResource: Int) =
    showErrorDialog(resources.getString(errorMessageResource))

fun Context.showErrorDialog(errorMessage: String) =
    AlertDialog.Builder(this)
        .setMessage(errorMessage)
        .create()
        .show()
