package com.android.androiddialog.dialog

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import androidx.core.graphics.toColorInt
import com.android.androiddialog.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.simple_dialog.view.*
import org.jetbrains.anko.internals.AnkoInternals
import org.jetbrains.anko.textColor

class SimpleDialog(val activity: Activity) : AlertDialog.Builder(activity) {
    private lateinit var customView: View
    private lateinit var dialog: AlertDialog

    init {
        setCustomView()
        setDialog()
    }

    private fun setCustomView() {
        customView = activity.layoutInflater.inflate(
            R.layout.simple_dialog,
            null
        )
        this.setView(customView)
    }

    private fun setDialog() {
        dialog = this
            .create()
            .apply {
                show()
                window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
    }

    open var title: String
        get() = AnkoInternals.noGetter()
        set(value) {
            customView.titleDialog.text = value
        }

    fun setTitleStyle(
        title: String = activity.getString(R.string.title_dialog),
        italic: Boolean = false,
        size: Int = customView.titleDialog.textSize.toInt(),
        color: Int = R.color.black
    ) {
        customView.titleDialog.apply {
            if (text.toString().isNotEmpty()) {
                text = title
            }
            if (italic) {
                setTypeface(typeface, Typeface.ITALIC)
            }
            setTextSize(TypedValue.COMPLEX_UNIT_SP, size.toFloat())
            setTextColor(activity.getColor(color))
        }
    }

    var titleFontSize: Int
        get() = customView.titleDialog.textSize.toInt()
        set(value) {
            customView.titleDialog.setTextSize(TypedValue.COMPLEX_UNIT_SP, value.toFloat())
        }

    var titleColor = activity.getColor(R.color.black)
        set(value) {
            customView.titleDialog.textColor = activity.getColor(value)
        }

    var content: String
        get() = customView.contentDialog.text.toString()
        set(value) {
            customView.contentDialog.text = value
        }

    var contentFontSize: Int
        get() = customView.titleDialog.textSize.toInt()
        set(value) {
            customView.contentDialog.setTextSize(TypedValue.COMPLEX_UNIT_SP, value.toFloat())
        }

    var contentColor = "#000000".toColorInt()
        set(value) {
            customView.contentDialog.textColor = value
        }

    fun setContentStyle(
        content: String = customView.titleDialog.text.toString(),
        italic: Boolean = false,
        size: Int = customView.contentDialog.textSize.toInt(),
        color: Int = R.color.black
    ) {

        customView.contentDialog.apply {
            if (text.toString().isNotEmpty()){
                text = content
            }
            if (italic) {
                setTypeface(typeface, Typeface.ITALIC)
            }
            setTextSize(TypedValue.COMPLEX_UNIT_SP, size.toFloat())
            setTextColor(activity.getColor(color))
        }
    }

    fun setImage(
        image: Int,
        height: Int = customView.imageDialog.height,
        width: Int = customView.imageDialog.width
    ) {
        showImage = true
        Glide.with(activity)
            .load(image)
            .into(customView.imageDialog)
    }

    fun setImage(
        imageUrl: String,
        height: Int = customView.imageDialog.height,
        width: Int = customView.imageDialog.width
    ) {
        showImage = true
        Glide.with(activity)
            .load(imageUrl)
            .into(customView.imageDialog)
    }

    fun setImage(
        image: Drawable,
        height: Int = customView.imageDialog.height,
        width: Int = customView.imageDialog.width
    ) {
        showImage = true
        Glide.with(activity)
            .load(image)
            .into(customView.imageDialog)
    }

    fun yesButton(title: String = "YES", callback: (dialog: AlertDialog) -> Unit) {
        customView.yesButtonDialog.text = title.toUpperCase()
        customView.yesButtonDialog.setOnClickListener {
            callback(dialog)
        }
    }


    fun noButton(title: String = "NO", callback: (dialog: AlertDialog) -> Unit) {
        customView.noButtonDialog.text = title.toUpperCase()
        customView.noButtonDialog.setOnClickListener {
            callback(dialog)
        }
    }

    fun okButton(callback: (dialog: AlertDialog) -> Unit){
        showOnlyOkButton()
        customView.okButtonDialog.setOnClickListener {
            callback(dialog)
        }
    }

    private fun showOnlyOkButton(){
        customView.noButtonDialog.visibility = View.GONE
        customView.yesButtonDialog.visibility = View.GONE
        customView.okButtonDialog.visibility = View.VISIBLE
    }

    var yesButtonTextColor = R.color.black
        set(value) {
            customView.yesButtonDialog.textColor = activity.getColor(value)
        }

    var noButtonTextColor = R.color.black
        set(value) {
            customView.noButtonDialog.textColor = activity.getColor(value)
        }

    private var showImage: Boolean
        get() = false
        set(value) {
            if (value) {
                customView.imageDialog.visibility = View.VISIBLE
            }
        }
}

fun Activity.simpleDialog(init: SimpleDialog.() -> Unit) {
    SimpleDialog(this).apply(init)
}