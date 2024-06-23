package com.jer.ch4_ch5.data.datasource.remote.retrofit.login

import retrofit2.http.Body
import retrofit2.http.POST

interface ReqresService {

    @POST("login")
    fun login(
        @Body loginBody: LoginBody,
    ): LoginResponse

    @POST("register")
    fun register(
        @Body registerBody: RegisterBody
    ): LoginResponse

}