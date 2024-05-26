package com.jer.ch4_ch5.data.repository.login

import com.jer.ch4_ch5.User
import com.jer.ch4_ch5.ui.login.LoginBody
import com.jer.ch4_ch5.ui.login.RegisterBody
import com.jer.ch4_ch5.ui.login.ReqresService
import kotlinx.coroutines.delay

class ImplementLoginRemote(private val reqresService: ReqresService): LoginRemoteSource {

    val user = listOf(
        User(username = "fajar", password = "12345678")
    )

    override suspend fun login(username: String, password: String): String {


        return if (user.contains(User(username, password))) {
            "qwertyuiopasdfghjklzxcvbnm"
        } else {
            reqresService.login(
                loginBody = LoginBody(username, password, username)
            ).token
//            throw UnsupportedOperationException("user belum registrasi")
//           Log.e("LoginRemote", "user belum tidak tersedia")
        }
    }





    override suspend fun register(email: String, username: String, password: String): String {
        return reqresService.register(
            RegisterBody(
                email = email,
                username = username,
                password = password,
            )
        ).token
    }
}