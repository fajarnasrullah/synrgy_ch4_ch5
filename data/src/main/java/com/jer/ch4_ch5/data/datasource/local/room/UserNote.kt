package com.jer.ch4_ch5.data.datasource.local.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class UserNote(

    @PrimaryKey(true)
    @ColumnInfo("id")
    var id: Int = 0,

    @ColumnInfo("nama")
    var nama: String? = null,

    @ColumnInfo("asal")
    var asal: String? = null,

    @ColumnInfo("prodi")
    var prodi: String? = null,

    @ColumnInfo("date")
    var date: String? = null

):Parcelable
