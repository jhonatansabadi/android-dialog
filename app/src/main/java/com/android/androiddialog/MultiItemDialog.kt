package com.android.androiddialog

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.androiddialog.adapter.MultiItemAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.multi_item_dialog.view.*
import org.jetbrains.anko.internals.AnkoInternals

class MultiItemDialog(val activity: Activity, val itens: MutableList<String>) : AlertDialog.Builder(activity) {
    private var customView: View
    private lateinit var dialog: AlertDialog
    private var builder = this

    init {
        customView = activity.layoutInflater.inflate(R.layout.multi_item_dialog, null)
        builder.setView(customView)
        onSetDialog()
        initRecyclerView()
    }

    private fun onSetDialog() {
        dialog = builder.create()
        dialog.show()
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun initRecyclerView() {
        customView.recyclerMultiChoice.layoutManager = LinearLayoutManager(activity)
        customView.recyclerMultiChoice.adapter = MultiItemAdapter(activity, itens)
        customView.recyclerMultiChoice.hasFixedSize()
        customView.recyclerMultiChoice.isNestedScrollingEnabled = true
    }

    var title: String
        get() = AnkoInternals.noGetter()
        set(value) {
            customView.customDialogTitleChoice.text = value
        }

    fun setImage(
        image: Int,
        height: Int = customView.customDialogImageChoice.height,
        width: Int = customView.customDialogImageChoice.width
    ) {
        showImage = true
        Glide.with(activity)
            .load(image)
            .into(customView.customDialogImageChoice)
    }

    private var showImage: Boolean
        get() = false
        set(value) {
            if (value) {
                customView.customDialogImageChoice.visibility = View.VISIBLE
            }
        }
}