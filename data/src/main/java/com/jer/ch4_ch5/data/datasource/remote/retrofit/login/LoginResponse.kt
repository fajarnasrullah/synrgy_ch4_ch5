package com.jer.ch4_ch5.data.datasource.remote.retrofit.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("token")
    val token: String
)
