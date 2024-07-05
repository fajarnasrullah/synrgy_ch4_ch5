package com.jer.ch4_ch5

import android.app.Application
import com.jer.ch4_ch5.di.Module
import com.jer.ch4_ch5.di.factory.ViewModelFactory
import com.jer.ch4_ch5.di.factory.koinViewModelModule
import com.jer.ch4_ch5.di.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication: Application() {

    lateinit var module: Module
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate() {
        super.onCreate()

        module = Module(this)
        viewModelFactory = ViewModelFactory(module)

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(koinModule, koinViewModelModule)

        }


    }

}