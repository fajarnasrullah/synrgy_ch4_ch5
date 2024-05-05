package com.jer.ch4_ch5.data.repository.login

interface LoginRepository {
    suspend fun login(username: String, password: String): String
    fun saveToken(token: String)
    fun loadToken(): String?
    fun deleteToken()
}