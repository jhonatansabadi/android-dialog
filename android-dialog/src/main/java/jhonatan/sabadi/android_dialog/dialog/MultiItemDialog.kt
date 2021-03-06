package com.android.androiddialog.dialog

import android.app.Activity
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.androiddialog.adapter.MultiItemAdapter
import com.android.androiddialog.interfaces.OnItemClickListener
import com.android.androiddialog.interfaces.OnRecyclerClickListener
import jhonatan.sabadi.android_dialog.R
import jhonatan.sabadi.android_dialog.dialog.BaseDialog
import kotlinx.android.synthetic.main.multi_item_dialog.view.*


class MultiItemDialog(
    activity: Activity,
    val itens: MutableList<String>,
    val icons: MutableList<Int>? = null,
    val icon: Int? = null
) : BaseDialog(activity, R.layout.multi_item_dialog), OnRecyclerClickListener {

    private var onItemClick: OnItemClickListener? = null

    init {
        initRecyclerView()
    }

    override var title: String
        get() = customView.titleDialog.text.toString()
        set(value) {
            customView.titleDialog.apply {
                visibility = View.VISIBLE
                text = value
            }
        }

    fun onItemClickListener(callback: (value: String, position: Int) -> Unit) {
        setOnItemClickListener(object : OnItemClickListener {
            override fun setOnItemClick(value: String, position: Int) {
                dialog.dismiss()
                callback(value, position)
            }
        })
    }

    private fun setOnItemClickListener(onItemClick: OnItemClickListener) {
        this.onItemClick = onItemClick
    }

    override fun setOnRecyclerClick(view: View, position: Int) {
        onItemClick?.setOnItemClick(itens[position], position)
    }

    private fun initRecyclerView() {
        val iconsfilled = getIconsFullfilled(icon)
        customView.recyclerViewDialog.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = MultiItemAdapter(itens, icons ?: iconsfilled, this@MultiItemDialog)
            setHasFixedSize(true)
            isNestedScrollingEnabled = true
//            addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager(activity).orientation))
        }
    }

    private fun getIconsFullfilled(icon: Int?): MutableList<Int> {
        val iconsfilled = mutableListOf<Int>()
        if (icons.isNullOrEmpty()) {
            itens.forEach {
                icon?.let {
                    iconsfilled?.add(icon)
                }
            }
        }
        return iconsfilled
    }
}

fun Activity.multiItemDialog(
    itens: MutableList<String>,
    icons: MutableList<Int>? = null,
    init: (MultiItemDialog.() -> Unit)
) {
    MultiItemDialog(this, itens, icons).apply(init)
}

fun Activity.multiItemDialog(
    itens: MutableList<String>,
    icon: Int? = null,
    init: (MultiItemDialog.() -> Unit)
) {
    MultiItemDialog(this, itens, null, icon).apply(init)
}
