package com.jer.ch4_ch5.ui.login

import android.content.Context
import com.jer.ch4_ch5.ui.art.retrofit.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClientLogin {

    companion object {

        //        const val BASE_URL = "https://collectionapi.metmuseum.org"
//        const val BASE_URL = "https://api.artic.edu"
        const val BASE_URL = "https://reqres.in/api/"
        fun getApiService(
//            java: Class<ReqresService>
            context: Context
        ): ReqresService {
            val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(ReqresService::class.java)
        }
    }

}