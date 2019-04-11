package com.android.androiddialog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.androiddialog.R
import com.android.androiddialog.interfaces.OnRecyclerClickListener
import kotlinx.android.synthetic.main.recycler_color_picker.view.*
import org.jetbrains.anko.backgroundColor

class ColorPickerAdapter(
    val context: Context,
    val colors: MutableList<Int>,
    val onRecyclerClickListener: OnRecyclerClickListener
) : RecyclerView.Adapter<ColorPickerAdapter.ColorPickerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ColorPickerViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(
                R.layout.recycler_color_picker,
                parent,
                false
            )
    )

    override fun getItemCount() = colors.size

    override fun onBindViewHolder(holder: ColorPickerViewHolder, position: Int) {
        holder.bind(colors[position])
    }

    class ColorPickerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(color: Int) {
            itemView.viewColor.backgroundColor = color
        }
    }

}

