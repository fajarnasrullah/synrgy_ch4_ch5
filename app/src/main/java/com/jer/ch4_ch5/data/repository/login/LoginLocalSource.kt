package com.jer.ch4_ch5.data.repository.login

interface LoginLocalSource {
    fun saveToken(token: String)
    fun loadtoken(): String?
    fun deleteToken()
}