package com.ziss.core.utils

import androidx.recyclerview.widget.DiffUtil
import com.ziss.core.domain.entities.Movie

class MovieDIffCallback(
    private val oldItemList: List<Movie>,
    private val newItemList: List<Movie>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldItemList.size
    }

    override fun getNewListSize(): Int {
        return newItemList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemList[oldItemPosition] == newItemList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItemList[oldItemPosition]
        val newItem = newItemList[newItemPosition]
        return oldItem.id == newItem.id
    }
}