package com.jer.ch4_ch5.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jer.ch4_ch5.data.datasource.local.room.UserNote
import com.jer.ch4_ch5.data.repository.students.NoteStudentsRepository

class GetAllNotesViewModel(application: Application): ViewModel() {

    private val repository: NoteStudentsRepository = NoteStudentsRepository(application)

    fun getAllNotes(): LiveData<List<UserNote>> = repository.getAllStudents()

}