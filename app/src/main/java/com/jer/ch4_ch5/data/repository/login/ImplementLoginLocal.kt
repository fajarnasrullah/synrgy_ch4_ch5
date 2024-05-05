package com.jer.ch4_ch5.data.repository.login

import android.content.SharedPreferences

class ImplementLoginLocal(
//    context: Context,
    private val sharedPreferences: SharedPreferences
): LoginLocalSource {

    companion object {
        const val KEY = "token"
        const val SP = "shared_preferences"
    }

//    private val preferences = context.getSharedPreferences(SP, Context.MODE_PRIVATE)



    override fun saveToken(token: String) {
//        preferences.edit().putString(KEY, token).apply()
        sharedPreferences.edit().putString(KEY, token).apply()
    }

    override fun loadtoken(): String? {
//        return preferences.getString(KEY, null)
        return sharedPreferences.getString(KEY, null)
    }

    override fun deleteToken() {
//        preferences.edit().remove(KEY).apply()
        sharedPreferences.edit().remove(KEY).apply()
    }
}