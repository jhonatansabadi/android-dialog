package com.android.androiddialog.dialog

import android.content.Context
import jhonatan.sabadi.android_dialog.R
import jhonatan.sabadi.android_dialog.dialog.BaseDialog

class SimpleDialog(activity: Context) :
    BaseDialog(activity, R.layout.simple_dialog) {
}

fun Context.simpleDialog(init: SimpleDialog.() -> Unit) {
    SimpleDialog(this).apply(init)
}