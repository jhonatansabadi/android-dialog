package com.android.androiddialog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.android.androiddialog.interfaces.OnRecyclerClickListener
import com.android.androiddialog.model.CheckedColor
import jhonatan.sabadi.android_dialog.R
import jhonatan.sabadi.android_dialog.databinding.RecyclerColorPickerBinding

class ColorPickerAdapter(
    private val onRecyclerClickListener: OnRecyclerClickListener
) : RecyclerView.Adapter<ColorPickerAdapter.ColorPickerViewHolder>() {

    private val checkedColors = mutableListOf<CheckedColor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorPickerViewHolder {
        val holder = ColorPickerViewHolder(
            RecyclerColorPickerBinding.inflate(
                LayoutInflater.from(parent.context),
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
        notifyDataSetChanged()
        checkItem(position)
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

    class ColorPickerViewHolder(
        private val binding: RecyclerColorPickerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(checkedColor: CheckedColor) {
            val color = try {
                ContextCompat.getColor(itemView.context, checkedColor.color)
            } catch (e: Exception) {
                checkedColor.color
            }
            color.setRoundedIcon()
            checkedColor.setCheckedIcon()

        }

        private fun Int.setRoundedIcon() {
            val drawable = itemView.context.getDrawable(R.drawable.color_round)
            drawable?.setTint(this)
            binding.viewColorRecycler.background = drawable
        }

        private fun CheckedColor.setCheckedIcon() {
            binding.viewCheckRecycler.isVisible = isChecked
        }
    }
}

