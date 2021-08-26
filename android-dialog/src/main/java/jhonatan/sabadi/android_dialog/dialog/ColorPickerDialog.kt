package com.android.androiddialog.dialog

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.androiddialog.adapter.ColorPickerAdapter
import com.android.androiddialog.interfaces.OnColorItemClickListener
import com.android.androiddialog.interfaces.OnRecyclerClickListener
import jhonatan.sabadi.android_dialog.R
import jhonatan.sabadi.android_dialog.dialog.BaseDialog

class ColorPickerDialog(
    activity: Context,
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
        setViews()
        initRecyclerView()
        onColorClickListener()
    }

    private fun setViews() {
        recyclerViewDialog = customView.findViewById(R.id.recyclerViewDialog)
    }


    private fun initRecyclerView() {
        recyclerViewDialog?.apply {
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

fun Context.colorPickerDialog(
    colors: MutableList<Int>,
    init: (ColorPickerDialog.() -> Unit)
) {
    ColorPickerDialog(this, colors).apply(init)
}
