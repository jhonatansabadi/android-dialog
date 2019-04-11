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
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.androiddialog.R
import com.android.androiddialog.adapter.ColorPickerAdapter
import com.android.androiddialog.interfaces.OnColorItemClickListener
import com.android.androiddialog.interfaces.OnRecyclerClickListener
import com.android.androiddialog.model.CheckedColor
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.color_picker_dialog.view.*
import org.jetbrains.anko.internals.AnkoInternals
import org.jetbrains.anko.textColor

class ColorPickerDialog(
    val activity: Activity,
    val colors: MutableList<Int>
) : AlertDialog.Builder(activity), OnRecyclerClickListener {

    private var checkedColors = mutableListOf<CheckedColor>()
    private lateinit var customView: View
    private lateinit var dialog: AlertDialog
    private var onColorClick: OnColorItemClickListener? = null
    private var selectedColor = -1
    private var selectedPosition = -1

    init {
        initColorChecked()
        setCustomView()
        initRecyclerView()
        setDialog()
        onColorClickListener()
        cancelButton()
    }

    private fun initColorChecked() {
        checkedColors.clear()
        colors.forEach {
            checkedColors.add(CheckedColor(color = it))
        }
    }

    private fun setCustomView() {
        customView = activity.layoutInflater.inflate(
            R.layout.color_picker_dialog,
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

    private fun initRecyclerView() {
        customView.recyclerViewDialog.apply {
            layoutManager = StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL)
            adapter = ColorPickerAdapter(activity, checkedColors, this@ColorPickerDialog)
            hasFixedSize()
            isNestedScrollingEnabled = true
        }
    }

    override fun setOnRecyclerClick(view: View, position: Int) {
        onColorClick?.setOnColorItemClick(view, colors[position], position)
    }

    fun onColorClickListener(callback: ((color: Int, position: Int) -> Unit)? = null) {
        setOnColorClickListener(object : OnColorItemClickListener {
            override fun setOnColorItemClick(view: View, color: Int, position: Int) {
                initColorChecked()
                checkedColors[position].checked = true
                initRecyclerView()
                selectedColor = color
                selectedPosition = position
                callback?.invoke(color, position)
            }
        })
    }

    private fun setOnColorClickListener(onColorClick: OnColorItemClickListener) {
        this.onColorClick = onColorClick
    }

    var title: String
        get() = AnkoInternals.noGetter()
        set(value) {
            showTitle = true
            customView.titleDialog.text = value
        }

    fun setTitleStyle(
        title: String = customView.titleDialog.text.toString(),
        italic: Boolean = false,
        size: Int = customView.titleDialog.textSize.toInt(),
        color: Int = R.color.black
    ) {
        showTitle = true
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

    var titleColor = "#000000".toColorInt()
        set(value) {
            customView.titleDialog.textColor = activity.getColor(value)
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


    private var showImage: Boolean
        get() = false
        set(value) {
            if (value) {
                customView.imageDialog.visibility = View.VISIBLE
            }
        }

    private var showTitle: Boolean
        get() = false
        set(value) {
            if (value) {
                customView.titleDialog.visibility = View.VISIBLE
            }
        }

    fun okButton(title: String = "ok", callback: (color: Int, position: Int) -> Unit) {
        customView.okButtonColorPicker.setOnClickListener {
            dialog.dismiss()
            callback(selectedColor, selectedPosition)
        }
    }

    fun cancelButton(callback: ((color: Int, position: Int) -> Unit)? = null) {
        customView.cancelButtonColorPicker.setOnClickListener {
            dialog.dismiss()
        }
    }
}

fun Activity.colorPickerDialog(
    colors: MutableList<Int>,
    init: (ColorPickerDialog.() -> Unit)
) {
    ColorPickerDialog(this, colors).apply(init)
}
