package com.jer.ch4_ch5.ui.art.response

import com.google.gson.annotations.SerializedName

data class SearchArtResponse(
//    val searchArt: List<DetailArtResponse>,
    @SerializedName("objectIDs")
    val objectIDs: List<DetailArtResponse>,
)
