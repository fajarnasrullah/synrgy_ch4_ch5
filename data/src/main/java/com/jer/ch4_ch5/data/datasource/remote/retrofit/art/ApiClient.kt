

package com.jer.ch4_ch5.data.datasource.remote.retrofit.art

import android.content.Context
import com.chuckerteam.chucker.api.BodyDecoder
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.google.gson.Gson
import com.google.gson.internal.GsonBuildConfig
import com.jer.ch4_ch5.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {


    companion object {

//        const val BASE_URL = "https://collectionapi.metmuseum.org"
//        const val BASE_URL = "https://api.artic.edu"
        const val BASE_URL = "https://www.rijksmuseum.nl/"



        fun getApiService(java: Class<ApiService>): ApiService {
            val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//
            val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_ART)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }



}



