package com.android.androiddialog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.androiddialog.interfaces.OnRecyclerClickListener
import com.android.androiddialog.model.CheckedColor
import jhonatan.sabadi.android_dialog.R
import kotlinx.android.synthetic.main.recycler_color_picker.view.*

class ColorPickerAdapter(
    val colors: MutableList<CheckedColor>,
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
        fun bind(checkedColor: CheckedColor) {
            var color: Int
            try {
                color = ContextCompat.getColor(itemView.context, checkedColor.color)
            } catch (e: Exception) {
                color = checkedColor.color
            }
            color.setRoundedIcon()
            checkedColor.setCheckedIcon()

        }

        private fun Int.setRoundedIcon() {
            val drawable = itemView.context.getDrawable(R.drawable.color_round)
            drawable?.setTint(this)
            itemView.viewColorRecycler.background = drawable
        }

        private fun CheckedColor.setCheckedIcon() {
            if (checked) {
                itemView.viewCheckRecycler.visibility = View.VISIBLE
            }
        }
    }
}

