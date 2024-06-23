package com.jer.ch4_ch5.data.datasource.remote.retrofit.art.response


import com.google.gson.annotations.SerializedName

data class WebImageX(
    @SerializedName("guid")
    val guid: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("offsetPercentageX")
    val offsetPercentageX: Int,
    @SerializedName("offsetPercentageY")
    val offsetPercentageY: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)