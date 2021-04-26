package com.example.demo.ui.componants.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.databinding.RowItemsBinding
import com.example.demo.pojo.ImageData


class ItemAdapter :
    PagingDataAdapter<ImageData, ItemAdapter.ItemViewHolder>(PHOTO_COMPARIZATION) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RowItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        if (getItem(position) != null) {
            holder.bindTo(getItem(position)!!)//---send data to viewholder according to position---
        }
    }

    //-----------viewholder class----------
    class ItemViewHolder(private val thisbinding: RowItemsBinding) :
        RecyclerView.ViewHolder(thisbinding.root) {
        fun bindTo(data: ImageData) {
            thisbinding.pojo = data //--------sending data to ui--
        }
    }

    companion object {
        //------------to check if data is same or not--------------
        private val PHOTO_COMPARIZATION = object : DiffUtil.ItemCallback<ImageData>() {
            override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean =
                oldItem == newItem
        }
    }

}