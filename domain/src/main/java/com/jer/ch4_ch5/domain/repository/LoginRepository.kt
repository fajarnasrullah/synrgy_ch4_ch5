package com.jer.ch4_ch5.domain.repository

interface LoginRepository {
    suspend fun login(username: String, password: String): String
    suspend fun register(email: String, username: String, password: String): String
    suspend fun saveToken(token: String)
    suspend fun loadToken(): String?
    suspend fun deleteToken()
}