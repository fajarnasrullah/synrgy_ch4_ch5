package com.jer.ch4_ch5

import android.content.Context

internal class UserPreferences(context: Context) {

    companion object {
        const val SP = "userpreferences"
        const val NAME = "name"
        const val PW = "password"

    }

    private val preferences = context.getSharedPreferences(SP, Context.MODE_PRIVATE)


    fun setUser(user: com.jer.ch4_ch5.domain.model.User) {
        val edit = preferences.edit()
        edit.putString(NAME, user.username)
        edit.putString(PW, user.password)
        edit.apply()
    }

    fun getUser(): com.jer.ch4_ch5.domain.model.User {
        val user = com.jer.ch4_ch5.domain.model.User()
        user.username = preferences.getString(NAME, "")
        user.password = preferences.getString(PW, "")
        return user
    }

}