package com.android.androiddialog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.RecyclerView
import com.android.androiddialog.R
import com.android.androiddialog.interfaces.OnColorItemClickListener
import com.android.androiddialog.interfaces.OnRecyclerClickListener
import kotlinx.android.synthetic.main.recycler_color_picker.view.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.backgroundDrawable
import java.lang.Exception

class ColorPickerAdapter(
    val context: Context,
    val colors: MutableList<Int>,
    val onRecyclerClickListener: OnRecyclerClickListener
) : RecyclerView.Adapter<ColorPickerAdapter.ColorPickerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorPickerViewHolder {
        val holder = ColorPickerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.recycler_color_picker,
                    parent,
                    false
                )
        )
        holder.itemView.setOnClickListener {
            onRecyclerClickListener.setOnRecyclerClick(
                holder.itemView,
                holder.adapterPosition
            )
        }
        return holder
    }

    override fun getItemCount() = colors.size

    override fun onBindViewHolder(holder: ColorPickerViewHolder, position: Int) {
        holder.bind(colors[position])
    }

    class ColorPickerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(color: Int) {
            var c: Int
            try {
                c = itemView.context.getColor(color)
            } catch (e: Exception) {
                c = color
            }

            val drawable = itemView.context.getDrawable(R.drawable.color_round)
            drawable.setTint(c)
            itemView.viewColorRecycler.background = drawable
        }
    }
}

