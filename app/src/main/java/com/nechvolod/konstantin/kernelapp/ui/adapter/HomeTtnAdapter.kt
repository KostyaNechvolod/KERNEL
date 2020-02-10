package com.nechvolod.konstantin.kernelapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nechvolod.konstantin.kernelapp.base.adapter.BaseAdapter
import com.nechvolod.konstantin.kernelapp.data.model.TtnModel
import com.nechvolod.konstantin.kernelapp.databinding.ItemTtnBinding
import java.text.SimpleDateFormat
import java.util.*

class HomeTtnAdapter: BaseAdapter<TtnModel, HomeTtnAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemTtnBinding.inflate(
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
                    setOnClickListener?.invoke(selectEntity)
                }
            }
        }
    }
    class ViewHolder(private val binding: ItemTtnBinding):
            RecyclerView.ViewHolder(binding.root){
        fun bind(item: TtnModel) {
            binding.entity = item
        }

        private fun parceDate(oldDate: String):String{
            var spf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US)
            val newDate = spf.parse(oldDate)
            spf = SimpleDateFormat("dd.MM.yyyy", Locale.US)
            return spf.format(newDate)
        }
    }
}