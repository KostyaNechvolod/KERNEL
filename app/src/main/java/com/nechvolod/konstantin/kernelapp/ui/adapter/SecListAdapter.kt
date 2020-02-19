package com.nechvolod.konstantin.kernelapp.ui.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nechvolod.konstantin.kernelapp.base.adapter.BaseAdapter
import com.nechvolod.konstantin.kernelapp.data.model.TtnModel
import com.nechvolod.konstantin.kernelapp.databinding.ItemTtnDetailSecCompBinding
import kotlinx.android.synthetic.main.item_ttn_detail_sec_comp.view.*


class SecListAdapter: BaseAdapter<TtnModel.Code, SecListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemTtnDetailSecCompBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            MyCustomEditTextListener()
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val selectEntity = getItem(position)
        holder.bind(selectEntity)
        holder.itemView.checkbox.setOnCheckedChangeListener { _, isChecked ->
            getItem(position).isGood = isChecked
        }
    }

    class ViewHolder(private val binding: ItemTtnDetailSecCompBinding, private val textListener: MyCustomEditTextListener):
        RecyclerView.ViewHolder(binding.root){
        fun bind(item: TtnModel.Code) {
            with(binding){
                entity = item
                checkbox.isChecked = item.isGood
                textListener.updatePosition(adapterPosition)
//                textView.addTextChangedListener(textListener)
            }
        }
    }

    inner class MyCustomEditTextListener : TextWatcher {
        private var position: Int = 0

        fun updatePosition(position: Int) {
            this.position = position
        }

        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {
            // no op
        }

        override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {

        }

        override fun afterTextChanged(editable: Editable) {
            getItem(position).codeNumber = editable.toString()
            // no op
        }
    }
}