package com.jer.ch4_ch5.data.datasource.remote.retrofit.art.response


import com.google.gson.annotations.SerializedName

data class DetailArtworkResponse(
    @SerializedName("artObject")
    val artObject: ArtObjectX,
//    @SerializedName("artObjectPage")
//    val artObjectPage: ArtObjectPage,
    @SerializedName("elapsedMilliseconds")
    val elapsedMilliseconds: Int
)