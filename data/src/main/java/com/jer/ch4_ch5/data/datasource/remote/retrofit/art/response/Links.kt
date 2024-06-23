package com.jer.ch4_ch5.data.datasource.remote.retrofit.art.response


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("search")
    val search: String
)