package com.jer.ch4_ch5.data.datasource.remote

import com.jer.ch4_ch5.domain.model.User
import com.jer.ch4_ch5.data.datasource.LoginRemoteSource
import com.jer.ch4_ch5.data.datasource.remote.retrofit.login.LoginBody
import com.jer.ch4_ch5.data.datasource.remote.retrofit.login.RegisterBody
import com.jer.ch4_ch5.data.datasource.remote.retrofit.login.ReqresService


class ImplementLoginRemote(private val reqresService: ReqresService): LoginRemoteSource {

    val user = listOf(

        User(username = "fajar", password = "12345678")
    )

    override suspend fun login(username: String, password: String): String {


        return if (user.contains(User(username, password))) {
            "qwertyuiopasdfghjklzxcvbnm"
        } else {
//            val response = reqresService.login(
//                loginBody = LoginBody(username, password, username)
//            )
//            return response.token ?: throw NullPointerException("Login response is null")
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