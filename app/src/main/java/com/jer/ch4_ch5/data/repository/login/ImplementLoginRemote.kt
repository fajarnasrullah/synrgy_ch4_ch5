package com.jer.ch4_ch5.data.repository.login

import com.jer.ch4_ch5.User
import kotlinx.coroutines.delay

class ImplementLoginRemote: LoginRemoteSource {

    val user = listOf(
        User(username = "fajar", password = "12345678")
    )

    override suspend fun login(username: String, password: String): String {
        delay(1000)
        val users = user.contains(User(username, password))

        if (users) {
            return "qwertyuiopasdfghjklzxcvbnm"
        }
        else{

            throw UnsupportedOperationException("user belum registrasi")
//           Log.e("LoginRemote", "user belum tidak tersedia")
        }

    }
}