package com.jer.ch4_ch5.data.repository.login

import com.jer.ch4_ch5.data.datasource.LoginLocalSource
import com.jer.ch4_ch5.data.datasource.LoginRemoteSource
import com.jer.ch4_ch5.domain.repository.LoginRepository

class ImplementLoginRepository(
//    context: Context
    private val loginRemote: LoginRemoteSource,
    private val loginLocal: LoginLocalSource,
): LoginRepository {



    override suspend fun login(username: String, password: String): String {

        return loginRemote.login(username, password)
    }

    override suspend fun register(email: String, username: String, password: String): String {
        return loginRemote.register(email, username, password)
    }


    override suspend fun saveToken(token: String) {
//        preferences.edit().putString(KEY, token).apply()

        loginLocal. saveToken(token)
    }

    override suspend fun loadToken(): String? {
//        return preferences.getString(KEY, null)

        return loginLocal.loadtoken()
    }

    override suspend fun deleteToken() {
//        preferences.edit().remove(KEY).apply()

        loginLocal.deleteToken()
    }

}