package com.jer.ch4_ch5

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesFactory {

    companion object {
        const val SHARED_PREFERENCES_NAME = "app shared preferences"
    }

    fun createSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

}