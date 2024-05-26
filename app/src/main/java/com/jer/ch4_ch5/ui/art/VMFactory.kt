package com.jer.ch4_ch5.ui.art

import android.app.Application
import androidx.lifecycle.ViewModelProvider



class VMFactory private constructor(private val application: Application): ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var INSTANCE: VMFactory? = null
        @JvmStatic
        fun getInstance(application: Application): VMFactory {
            if (INSTANCE == null) {
                synchronized(VMFactory::class.java) {
                    INSTANCE = VMFactory(application)
                }
            }
            return INSTANCE as VMFactory
        }
    }



}