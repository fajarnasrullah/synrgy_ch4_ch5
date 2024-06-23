package com.jer.ch4_ch5.data.datasource.remote.retrofit.art

import com.jer.ch4_ch5.data.datasource.remote.retrofit.art.response.AllArtResponse
import com.jer.ch4_ch5.data.datasource.remote.retrofit.art.response.DetailArtResponse
import com.jer.ch4_ch5.data.datasource.remote.retrofit.art.response.DetailArtworkResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

//    @GET("public/collection/v1/objects")
//    fun getAllArtworks(
////        objects: Int
//    ):
////            Call<List<ArtResponse>>
//            Call<List<DetailArtResponse>>

    companion object {
        const val KEY = "bocqNoAa"
        const val ITEM_PER_PAGE = "ps=50"
    }
    @GET("api/en/collection?key=$KEY&$ITEM_PER_PAGE")
    fun getAllArtworks(): Call<AllArtResponse>



    @GET("public/collection/v1/search")
    fun searchArtworks(
        @Query("q") query: Int
    ):
            Call<List<DetailArtResponse>>
//            Call<SearchArtResponse>
//            Call<ArtResponse>

//    @GET("public/collection/v1/objects/{objectId}")
//    fun getDetailArtworks(@Path("objectId") objectId: Int): Call<DetailArtResponse>

    @GET("api/en/collection/{id}?key=$KEY")
    fun getDetailArtworks(@Path("id") id: String): Call<DetailArtworkResponse>
}