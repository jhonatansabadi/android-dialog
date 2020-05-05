package com.android.androiddialog.dialog

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import jhonatan.sabadi.android_dialog.R
import jhonatan.sabadi.android_dialog.dialog.BaseDialog
import kotlinx.android.synthetic.main.simple_dialog.view.*
import org.jetbrains.anko.internals.AnkoInternals
import org.jetbrains.anko.textColor

class SimpleDialog(activity: Activity) :
    BaseDialog(activity, R.layout.simple_dialog) {
}

fun Activity.simpleDialog(init: SimpleDialog.() -> Unit) {
    SimpleDialog(this).apply(init)
}