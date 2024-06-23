package com.jer.ch4_ch5.data.datasource

interface LoginLocalSource {
    suspend fun saveToken(token: String)
    suspend fun loadtoken(): String?
    suspend fun deleteToken()
}