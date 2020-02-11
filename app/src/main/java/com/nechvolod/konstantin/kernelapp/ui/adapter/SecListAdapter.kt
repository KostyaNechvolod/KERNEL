package com.nechvolod.konstantin.kernelapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nechvolod.konstantin.kernelapp.base.adapter.BaseAdapter
import com.nechvolod.konstantin.kernelapp.data.model.TtnModel
import com.nechvolod.konstantin.kernelapp.databinding.ItemTtnDetailSecCompBinding


class SecListAdapter: BaseAdapter<TtnModel.Code, SecListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemTtnDetailSecCompBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val selectEntity = getItem(position)
        holder.bind(selectEntity)
        holder.itemView.run {
            setOnClickListener {
                if (position != RecyclerView.NO_POSITION) {

                }
            }
        }
    }
    class ViewHolder(private val binding: ItemTtnDetailSecCompBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(item: TtnModel.Code) {
            binding.entity = item
            binding.checkbox.isChecked = item.isGood
        }
    }
}