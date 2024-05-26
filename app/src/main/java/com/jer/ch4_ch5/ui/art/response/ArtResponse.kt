package com.jer.ch4_ch5.ui.art.response


import com.google.gson.annotations.SerializedName

data class ArtResponse(
    @SerializedName("objectIDs")
    val objectIDs: List<Int>,
//    @SerializedName("total")
//    val total: Int
)