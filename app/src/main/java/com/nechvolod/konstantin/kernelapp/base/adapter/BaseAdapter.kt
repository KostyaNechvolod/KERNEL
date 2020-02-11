package com.nechvolod.konstantin.kernelapp.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<M, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    var setOnClickListener: ((M) -> Unit)? = null

    private var items = mutableListOf<M>()

    override fun getItemCount(): Int = this.items.size

    fun add(item: M) {
        this.items.add(item)
        notifyItemInserted(itemCount - 1)
    }

    fun add(items: List<M>) {
        val insertPosition = this.itemCount
        this.items.addAll(items)
        notifyItemRangeInserted(insertPosition, items.size)
    }

    fun add(position: Int, item: M) {
        this.items.add(position - 1, item)
        notifyItemRangeInserted(position, itemCount - 1)
    }

    fun replace(position: Int, item: M) {
        this.items.set(position, item)
        notifyItemInserted(position)
    }

    fun replace(items: List<M>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        this.items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    fun getItem(position: Int): M = this.items[position]

    fun getItems(): List<M> = this.items

    protected fun inflate(parent: ViewGroup, @LayoutRes layoutRes: Int) = parent.inflate(layoutRes)

    fun ViewGroup.inflate(layoutRes: Int, attach: Boolean = false): View? =
        LayoutInflater.from(context).inflate(layoutRes, this, attach)
            ?: throw IllegalArgumentException("ViewHolder not found, view = null")
}