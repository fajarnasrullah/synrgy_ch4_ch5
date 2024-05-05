package com.jer.ch4_ch5.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.InternalCoroutinesApi

class VMFactoryRoom private constructor(private val application: Application): ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var INSTANCE : VMFactoryRoom? = null

        @OptIn(InternalCoroutinesApi::class)
        @JvmStatic
        fun getInstance(application: Application): VMFactoryRoom {
            if (INSTANCE == null) {
                kotlinx.coroutines.internal.synchronized(VMFactoryRoom::class.java) {
                    INSTANCE = VMFactoryRoom(application)
                }
            }
            return INSTANCE as VMFactoryRoom
        }
    }


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(application) as T
        } else if (modelClass.isAssignableFrom(GetAllNotesViewModel::class.java)) {
            return GetAllNotesViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

}