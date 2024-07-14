package com.jer.ch4_ch5.ui.art

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jer.ch4_ch5.data.datasource.remote.retrofit.art.response.AllArtResponse
import com.jer.ch4_ch5.data.datasource.remote.retrofit.art.response.ArtObject
import com.jer.ch4_ch5.data.datasource.remote.retrofit.art.response.ArtObjectX
import com.jer.ch4_ch5.data.datasource.remote.retrofit.art.response.DetailArtResponse
import com.jer.ch4_ch5.data.datasource.remote.retrofit.art.response.DetailArtworkResponse
import com.jer.ch4_ch5.data.datasource.remote.retrofit.art.ApiClient
import com.jer.ch4_ch5.data.datasource.remote.retrofit.art.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtViewModel(
//    private val loginRepository: LoginRepository
): ViewModel() {


    companion object {
        const val TAG = "ArtViewModel"
        const val EXAMPLE = 437000
    }

    private val searchQuery = MutableLiveData<Int>()
//    private val _listArt = MutableLiveData<List<DetailArtResponse>>()
//    val listArt: LiveData<List<DetailArtResponse>> = _listArt
    private val _listArt = MutableLiveData<List<ArtObject>>()
    val listArt: LiveData<List<ArtObject>> = _listArt

//    private val _detaiArt = MutableLiveData<DetailArtResponse>()
//    val detailArt: LiveData<DetailArtResponse> = _detaiArt
//
    private val _detailArt = MutableLiveData<ArtObjectX>()
    val detailArt: LiveData<ArtObjectX> = _detailArt


//    init {
//        searchQuery.observeForever { query ->
//
//                findArt(query)
//
//        }
//    }


//    fun setSearchQuery(query: Int) {
//        searchQuery.value = query
//    }
    fun findArt(query: Int){
        val fromApiClient = ApiClient.getApiService(ApiService::class.java).searchArtworks(query)
        fromApiClient.enqueue(object : Callback<List<DetailArtResponse>> {
            //            override fun onResponse(p0: Call<SearchArtResponse>, p1: Response<SearchArtResponse>) {
//                if (p1.isSuccessful) {
//                    _listArt.value = p1.body()?.objectIDs
//                } else {
//                    Log.e(TAG, "Failure: ${p1.message()}")
//                }
//            }
//
//            override fun onFailure(p0: Call<SearchArtResponse>, p1: Throwable) {
//                Log.e(TAG, "Failure: ${p1.message}")
//
//            }
            override fun onResponse(
                p0: Call<List<DetailArtResponse>>,
                p1: Response<List<DetailArtResponse>>
            ) {
                if (p1.isSuccessful) {
//                    _listArt.value = p1.body()
                } else {
                    Log.e(TAG, "Failure: ${p1.message()}")

                }
            }

            override fun onFailure(p0: Call<List<DetailArtResponse>>, p1: Throwable) {
                Log.e(TAG, "Failure: ${p1.message}")

            }


        })
    }

//    fun findDetailArt(){
//        ApiClient.getApiService(ApiService::class.java).getDetailArtworks(EXAMPLE).enqueue(object : Callback<DetailArtResponse> {
//            override fun onResponse(p0: Call<DetailArtResponse>, p1: Response<DetailArtResponse>) {
//                if (p1.isSuccessful) {
//                    _detaiArt.value = p1.body()
//                } else {
//                    Log.e(TAG, "Failure: ${p1.message()}")
//                }
//            }
//
//            override fun onFailure(p0: Call<DetailArtResponse>, p1: Throwable) {
//                Log.e(TAG, "onFailure: ${p1.message}")
//            }
//
//        })
//    }

    fun findDetailArt(id: String) {
        ApiClient.getApiService(ApiService::class.java) .getDetailArtworks(id).enqueue(object : Callback<DetailArtworkResponse> {
            override fun onResponse(
                p0: Call<DetailArtworkResponse>,
                p1: Response<DetailArtworkResponse>
            ) {
                if (p1.isSuccessful) {
                    _detailArt.value = p1.body()?.artObject
                } else {
                    Log.e(TAG, "Failure: ${p1.message()}")
                }
            }

            override fun onFailure(p0: Call<DetailArtworkResponse>, p1: Throwable) {
                Log.e(TAG, "Failure: ${p1.message}")
            }

        })
    }


    fun findAllArts(){
        ApiClient.getApiService(ApiService::class.java).getAllArtworks().enqueue(object: Callback<AllArtResponse> {
            override fun onResponse(p0: Call<AllArtResponse>, p1: Response<AllArtResponse>) {
                if (p1.isSuccessful) {
                    _listArt.value = p1.body()?.artObjects
                } else {
                    Log.e(TAG, "Failure: ${p1.message()}")
                }
            }

            override fun onFailure(p0: Call<AllArtResponse>, p1: Throwable) {
                Log.e(TAG, "Failure: ${p1.message}")
            }

        })
    }




}