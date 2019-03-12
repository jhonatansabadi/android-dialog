package com.android.androiddialog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.androiddialog.R
import kotlinx.android.synthetic.main.recycler_multi_item.view.*

class MultiItemAdapter(
    val context: Context,
    val itens: MutableList<String>
) : RecyclerView.Adapter<MultiItemAdapter.MultiItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiItemViewHolder {
        return MultiItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_multi_item, parent, false)
        )
    }

    override fun getItemCount() = itens.size

    override fun onBindViewHolder(holder: MultiItemViewHolder, position: Int) {
        holder.bind(itens[position])
    }

    class MultiItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: String){
            itemView.apply {
                titleRecyclerDialog.text = item
                iconRecyclerDialog.visibility = View.GONE
            }
        }
    }

}