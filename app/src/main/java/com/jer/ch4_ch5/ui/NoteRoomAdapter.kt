package com.jer.ch4_ch5.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jer.ch4_ch5.data.datasource.local.room.UserNote
import com.jer.ch4_ch5.databinding.ItemNoteBinding

class NoteRoomAdapter:RecyclerView.Adapter<NoteRoomAdapter.RoomViewHolder>() {

    inner class RoomViewHolder(private val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(userNote: UserNote) {
            binding.tvItemDate.text = userNote.date
            binding.tvItemTitle.text = userNote.nama
            binding.tvItemCity.text = userNote.asal
            binding.tvItemMajor.text = userNote.prodi
            binding.cvItemNote.setOnClickListener {
                val intent = Intent(it.context, FormNoteActivity::class.java)
                intent.putExtra(FormNoteActivity.EXTRA_NOTE, userNote)
                it.context.startActivity(intent)
            }
        }

    }

    private val listNotes = ArrayList<UserNote>()
    fun setListNotes(listNotes: List<UserNote>) {
        val diffCallback = NoteDiffCallback(this.listNotes, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listNotes.clear()
        this.listNotes.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }

}