package com.jer.ch4_ch5.data.datasource.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface DaoUserNote {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(userNote: UserNote)

    @Update
    fun update(userNote: UserNote)

    @Delete
    fun delete(userNote: UserNote)

    @Query("SELECT * FROM UserNote ORDER BY id ASC")
    fun getAllStudents(): LiveData<List<UserNote>>

}