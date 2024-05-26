package com.jer.ch4_ch5.ui.login

import com.google.gson.annotations.SerializedName


data class LoginBody(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)
