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
import jhonatan.sabadi.android_dialog.dialog.BaseDialog
import kotlinx.android.synthetic.main.color_picker_dialog.view.*
import kotlinx.android.synthetic.main.color_picker_dialog.view.recyclerViewDialog
import kotlinx.android.synthetic.main.multi_item_dialog.view.*

class ColorPickerDialog(
    activity: Activity,
    val colors: MutableList<Int>
) : BaseDialog(activity, R.layout.color_picker_dialog), OnRecyclerClickListener {

    private var onColorClick: OnColorItemClickListener? = null
    private var selectedColor = -1
    private var selectedPosition = -1

    private val colorPickerAdapter by lazy {
        ColorPickerAdapter(this).apply {
            submitList(colors)
        }
    }

    init {
        initRecyclerView()
        onColorClickListener()
    }


    private fun initRecyclerView() {
        customView.recyclerViewDialog.apply {
            layoutManager = StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL)
            adapter = colorPickerAdapter
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
                colorPickerAdapter.setItemChecked(position)
                selectedColor = color
                selectedPosition = position
                callback?.invoke(color, position)
            }
        })
    }

    private fun setOnColorClickListener(onColorClick: OnColorItemClickListener) {
        this.onColorClick = onColorClick
    }
}

fun Activity.colorPickerDialog(
    colors: MutableList<Int>,
    init: (ColorPickerDialog.() -> Unit)
) {
    ColorPickerDialog(this, colors).apply(init)
}
