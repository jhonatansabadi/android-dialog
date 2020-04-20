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
    private val onRecyclerClickListener: OnRecyclerClickListener
) : RecyclerView.Adapter<ColorPickerAdapter.ColorPickerViewHolder>() {

    private val checkedColors = mutableListOf<CheckedColor>()

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

    fun submitList(colors: MutableList<Int>) {
        checkedColors.clear()
        colors.forEach {
            checkedColors.add(CheckedColor(color = it))
        }
    }

    fun setItemChecked(position: Int) {
        clearCheckedItens()
        checkItem(position)
        notifyItemChanged(position)
    }

    private fun checkItem(position: Int) {
        checkedColors[position].isChecked = true
    }

    private fun clearCheckedItens() {
        checkedColors.forEach {
            it.isChecked = false
        }
    }

    override fun getItemCount() = checkedColors.size

    override fun onBindViewHolder(holder: ColorPickerViewHolder, position: Int) {
        holder.bind(checkedColors[position])
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
            if (isChecked) {
                itemView.viewCheckRecycler.visibility = View.VISIBLE
            }
        }
    }
}

