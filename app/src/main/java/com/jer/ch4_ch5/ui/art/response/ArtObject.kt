package com.jer.ch4_ch5.ui.art.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


data class ArtObject(
    @SerializedName("hasImage")
    val hasImage: Boolean,
//    @SerializedName("headerImage")
//    val headerImage: HeaderImage,
    @SerializedName("id")
    val id: String,
//    @SerializedName("links")
//    val links: Links,
    @SerializedName("longTitle")
    val longTitle: String,
    @SerializedName("objectNumber")
    val objectNumber: String,
    @SerializedName("permitDownload")
    val permitDownload: Boolean,
    @SerializedName("principalOrFirstMaker")
    val principalOrFirstMaker: String,
    @SerializedName("productionPlaces")
    val productionPlaces: List<String>,
    @SerializedName("showImage")
    val showImage: Boolean,
    @SerializedName("title")
    val title: String,
    @SerializedName("webImage")

    val webImage: WebImage
)