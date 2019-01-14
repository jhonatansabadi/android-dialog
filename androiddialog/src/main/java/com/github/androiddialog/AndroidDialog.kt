package com.github.androiddialog

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import androidx.core.graphics.toColorInt
import com.bumptech.glide.Glide
import org.jetbrains.anko.internals.AnkoInternals
import kotlinx.android.synthetic.main.android_dialog.view.*
import org.jetbrains.anko.textColor

class AndroidDialog(val activity: Activity): AlertDialog.Builder(activity){
    private var customView: View
    private var dialog: AlertDialog

    init {

        //init
        customView = activity.layoutInflater.inflate(R.layout.android_dialog, null)

        this.setView(customView)
        dialog = this.create().apply {
            show()
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    var title: String
        get() = AnkoInternals.noGetter()
        set(value) {
            customView.customDialogTitle.text = value
        }


    fun setTitleStyle(
        title: String = customView.customDialogTitle.text.toString(),
        italic: Boolean = false,
        size: Int= customView.customDialogTitle.textSize.toInt(),
        color: Int = R.color.black
    ) {

        customView.customDialogTitle.apply {
            if(this.text.toString().isNotEmpty()) this.text = title
            if (italic) this.setTypeface(this.typeface, Typeface.ITALIC)
            this.setTextSize(TypedValue.COMPLEX_UNIT_SP, size.toFloat())
            this.setTextColor(activity.getColor(color))
        }
    }

    var titleFontSize: Int
        get() = customView.customDialogTitle.textSize.toInt()
        set(value) {
            customView.customDialogTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, value.toFloat())
        }

    var titleColor = "#000000".toColorInt()
        set(value) {
            customView.customDialogTitle.textColor = activity.getColor(value)
        }

    var content: String
        get() = customView.customDialogDescription.text.toString()
        set(value) {
            customView.customDialogDescription.text = value
        }

    var contentFontSize: Int
        get() = customView.customDialogTitle.textSize.toInt()
        set(value) {
            customView.customDialogDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, value.toFloat())
        }

    var contentColor = "#000000".toColorInt()
        set(value) {
            customView.customDialogDescription.textColor = value
        }

    fun setContentStyle(
        content: String = customView.customDialogTitle.text.toString(),
        italic: Boolean = false,
        size: Int = customView.customDialogDescription.textSize.toInt(),
        color: Int = R.color.black
    ) {

        customView.customDialogDescription.apply {
            if(this.text.toString().isNotEmpty()) this.text = content
            if (italic) this.setTypeface(this.typeface, Typeface.ITALIC)
            this.setTextSize(TypedValue.COMPLEX_UNIT_SP, size.toFloat())
            this.setTextColor(activity.getColor(color))
        }
    }

    fun setImage(
        image: Int,
        height: Int = customView.customDialogImage.height,
        width: Int = customView.customDialogImage.width
    ) {
        showImage = true
        Glide.with(activity)
            .load(image)
            .into(customView.customDialogImage)
    }

    fun setImage(
        imageUrl: String,
        height: Int = customView.customDialogImage.height,
        width: Int = customView.customDialogImage.width
    ) {
        showImage = true
        Glide.with(activity)
            .load(imageUrl)
            .into(customView.customDialogImage)
    }

    fun setImage(
        image: Drawable,
        height: Int = customView.customDialogImage.height,
        width: Int = customView.customDialogImage.width
    ) {
        showImage = true
        Glide.with(activity)
            .load(image)
            .into(customView.customDialogImage)
    }

    fun yesButton(title: String = "Yes", callback: (dialog: AlertDialog) -> Unit) {
        customView.customDialogYesButton.text = title.toUpperCase()
        customView.customDialogYesButton.setOnClickListener {
            callback(dialog)
        }
    }


    fun noButton(title: String = "No", callback: (dialog: AlertDialog) -> Unit) {
        customView.customDialogNoButton.text = title.toUpperCase()
        customView.customDialogNoButton.setOnClickListener {
            callback(dialog)
        }
    }

    var yesButtonTextColor = R.color.black
        set(value) {
            customView.customDialogYesButton.textColor = activity.getColor(value)
        }

    var noButtonTextColor = R.color.black
        set(value) {
            customView.customDialogNoButton.textColor = activity.getColor(value)
        }

    var showImage: Boolean
        get() = false
        set(value) {
            if (value) {
                customView.customDialogImage.visibility = View.VISIBLE
            }
        }
}