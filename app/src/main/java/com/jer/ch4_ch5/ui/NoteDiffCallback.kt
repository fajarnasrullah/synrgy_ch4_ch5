package com.jer.ch4_ch5.ui

import androidx.recyclerview.widget.DiffUtil
import com.jer.ch4_ch5.data.datasource.room.UserNote

class NoteDiffCallback(private val oldListNote: List<UserNote>, private val newListNote: List<UserNote>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldListNote.size

    override fun getNewListSize(): Int = newListNote.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldListNote[oldItemPosition].id == newListNote[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldListNote[oldItemPosition]
        val newItem = newListNote[newItemPosition]
        return oldItem.nama == newItem.nama && oldItem.asal == newItem.asal && oldItem.prodi == newItem.prodi
    }
}