package com.jer.ch4_ch5.data.datasource

interface LoginRemoteSource {
    suspend fun login(username: String, password: String) : String
    suspend fun register(email: String, username: String, password: String) : String
}