package com.jer.ch4_ch5.ui.art.response


import com.google.gson.annotations.SerializedName

data class AllArtResponse(
    @SerializedName("artObjects")
    val artObjects: List<ArtObject>,
    @SerializedName("count")
    val count: Int,
//    @SerializedName("countFacets")
//    val countFacets: CountFacets,
    @SerializedName("elapsedMilliseconds")
    val elapsedMilliseconds: Int,
//    @SerializedName("facets")
//    val facets: List<Facet>
)