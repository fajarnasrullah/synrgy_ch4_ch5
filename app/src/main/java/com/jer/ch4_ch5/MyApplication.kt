package com.jer.ch4_ch5

import android.app.Application
import com.jer.ch4_ch5.di.Module
import com.jer.ch4_ch5.di.factory.ViewModelFactory

class MyApplication: Application() {

    lateinit var module: Module
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate() {
        super.onCreate()

        module = Module(this)
        viewModelFactory = ViewModelFactory(module)

    }

}