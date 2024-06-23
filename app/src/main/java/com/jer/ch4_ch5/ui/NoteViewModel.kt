package com.jer.ch4_ch5.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import com.jer.ch4_ch5.data.datasource.local.room.UserNote
import com.jer.ch4_ch5.data.repository.students.NoteStudentsRepository

class NoteViewModel(application: Application): ViewModel() {

    private val repository: NoteStudentsRepository = NoteStudentsRepository(application)

    fun insert(userNote: UserNote) {
        repository.insert(userNote)
    }

    fun update(userNote: UserNote) {
        repository.update(userNote)
    }

    fun delete(userNote: UserNote) {
        repository.delete(userNote)
    }

}