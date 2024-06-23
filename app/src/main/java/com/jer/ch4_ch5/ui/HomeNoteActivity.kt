package com.jer.ch4_ch5.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jer.ch4_ch5.databinding.ActivityHomeNoteBinding


class HomeNoteActivity : AppCompatActivity() {

    private var _binding: ActivityHomeNoteBinding? = null
    private val binding get() = _binding
    private lateinit var adapter: NoteRoomAdapter
//    private lateinit var user: User
//    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeNoteBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val viewModel = obtainViewModel(this@HomeNoteActivity)
        viewModel.getAllNotes().observe(this) {notes ->
            if (notes != null) {
                adapter.setListNotes(notes)
            }
        }

        adapter = NoteRoomAdapter()
        binding?.rvNote?.layoutManager = LinearLayoutManager(this)
        binding?.rvNote?.setHasFixedSize(true)
        binding?.rvNote?.adapter = adapter

        binding?.fabAdd?.setOnClickListener {
            val intent = Intent(this@HomeNoteActivity, FormNoteActivity::class.java)
            startActivity(intent)
        }




//        userPreferences = UserPreferences(this)
//        user = userPreferences.getUser()
//        viewUsername(user)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

//    fun viewUsername(user: User) {
//        binding.tvUsername.text = "Hello ${user.username}"
//    }

    private fun obtainViewModel(activity: AppCompatActivity): GetAllNotesViewModel {
        val factory = VMFactoryRoom.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(GetAllNotesViewModel::class.java)
    }

}