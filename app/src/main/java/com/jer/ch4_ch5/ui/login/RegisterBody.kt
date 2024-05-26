package com.jer.ch4_ch5.ui.login

import com.google.gson.annotations.SerializedName

data class RegisterBody(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)
