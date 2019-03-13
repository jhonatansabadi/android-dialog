package com.android.androiddialog

import android.app.Activity
import android.app.AlertDialog
import android.app.SharedElementCallback
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.androiddialog.adapter.MultiItemAdapter
import com.android.androiddialog.interfaces.OnItemClickListener
import com.android.androiddialog.interfaces.OnRecyclerClickListener
import com.bumptech.glide.Glide
import com.github.androiddialog.R
import kotlinx.android.synthetic.main.multi_item_dialog.view.*
import org.jetbrains.anko.internals.AnkoInternals
import org.jetbrains.anko.textColor


class MultiItemDialog(
    val activity: Activity,
    val itens: MutableList<String>,
    val icons: MutableList<Int>? = null
) : AlertDialog.Builder(activity), OnRecyclerClickListener {


    private var customView: View
    private lateinit var dialog: AlertDialog
    private val builder = this
    private var onItemClick: OnItemClickListener? = null

    init {
        customView = activity.layoutInflater.inflate(R.layout.multi_item_dialog, null)
        builder.setView(customView)
        initRecyclerView()
        onSetDialog()
    }

    fun onItemClickListener(callback: (value: String, position: Int) -> Unit ){
        setOnItemClickListener(object : OnItemClickListener {
            override fun setOnItemClick(value: String, position: Int) {
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

    private fun onSetDialog() {
        dialog = builder.create()
        dialog.show()
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun initRecyclerView() {
        customView.recyclerViewDialog.layoutManager = LinearLayoutManager(activity)
        customView.recyclerViewDialog.adapter = MultiItemAdapter(activity, itens, icons,this)
        customView.recyclerViewDialog.hasFixedSize()
        customView.recyclerViewDialog.isNestedScrollingEnabled = true
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
            if (this.text.toString().isNotEmpty()) this.text = title
            if (italic) this.setTypeface(this.typeface, Typeface.ITALIC)
            this.setTextSize(TypedValue.COMPLEX_UNIT_SP, size.toFloat())
            this.setTextColor(activity.getColor(color))
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

}