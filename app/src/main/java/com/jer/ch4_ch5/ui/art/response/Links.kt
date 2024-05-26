package com.jer.ch4_ch5.ui.art.response


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("search")
    val search: String
)