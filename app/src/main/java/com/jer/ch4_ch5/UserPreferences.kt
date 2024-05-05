package com.jer.ch4_ch5

import android.content.Context

internal class UserPreferences(context: Context) {

    companion object {
        const val SP = "usserpreferences"
        const val NAME = "name"
        const val PW = "password"

    }

    private val preferences = context.getSharedPreferences(SP, Context.MODE_PRIVATE)


    fun setUser(user: User) {
        val edit = preferences.edit()
        edit.putString(NAME, user.username)
        edit.putString(PW, user.password)
        edit.apply()
    }

    fun getUser(): User {
        val user = User()
        user.username = preferences.getString(NAME, "")
        user.password = preferences.getString(PW, "")
        return user
    }

}