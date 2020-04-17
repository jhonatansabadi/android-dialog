package com.android.androiddialog.dialog

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.androiddialog.adapter.ColorPickerAdapter
import com.android.androiddialog.interfaces.OnColorItemClickListener
import com.android.androiddialog.interfaces.OnRecyclerClickListener
import com.android.androiddialog.model.CheckedColor
import com.bumptech.glide.Glide
import jhonatan.sabadi.android_dialog.R
import kotlinx.android.synthetic.main.color_picker_dialog.view.*

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
                window?.attributes?.windowAnimations = R.style.DialogAnimation
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
    }

    private fun initRecyclerView() {
        customView.recyclerViewDialog.apply {
            layoutManager = StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL)
            adapter = ColorPickerAdapter(checkedColors, this@ColorPickerDialog)
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

    fun defaultColorButton(callback: () -> Unit) {
        customView.defaultColorButtonColorPicker.setOnClickListener {
            dialog.dismiss()
            callback()
        }
    }
}

fun Activity.colorPickerDialog(
    colors: MutableList<Int>,
    init: (ColorPickerDialog.() -> Unit)
) {
    ColorPickerDialog(this, colors).apply(init)
}