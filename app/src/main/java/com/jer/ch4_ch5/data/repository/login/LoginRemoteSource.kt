package com.jer.ch4_ch5.data.repository.login

interface LoginRemoteSource {
    suspend fun login(username: String, password: String) : String
}