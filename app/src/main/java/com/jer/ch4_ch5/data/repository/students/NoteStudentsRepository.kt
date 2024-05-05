package com.jer.ch4_ch5.data.repository.students

import android.app.Application
import androidx.lifecycle.LiveData
import com.jer.ch4_ch5.data.datasource.room.NoteRoomDatabase
import com.jer.ch4_ch5.data.datasource.room.UserNote
import com.jer.ch4_ch5.data.datasource.room.DaoUserNote
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteStudentsRepository(application: Application) {

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    private val daoUserNote: DaoUserNote

    init {
        val db = NoteRoomDatabase.getDatabase(application)
        daoUserNote = db.daoUser()
    }

    fun insert(userNote: UserNote) {
        executorService.execute {
            daoUserNote.insert(userNote)
        }
    }

    fun update(userNote: UserNote) {
        executorService.execute {
            daoUserNote.update(userNote)
        }
    }

    fun delete(userNote: UserNote) {
        executorService.execute {
            daoUserNote.delete(userNote)
        }
    }

    fun getAllStudents(): LiveData<List<UserNote>>{
        return daoUserNote.getAllStudents()
    }

}