package com.jer.ch4_ch5.data.datasource.remote.retrofit.login

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.google.gson.Gson
import com.jer.ch4_ch5.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//class ApiClientLogin {
//
//    companion object {
//
//        //        const val BASE_URL = "https://collectionapi.metmuseum.org"
////        const val BASE_URL = "https://api.artic.edu"
//        const val BASE_URL = "https://reqres.in/api/"
//        fun getApiService(
////            java: Class<ReqresService>
//            context: Context
//        ): ReqresService {
//            val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//            val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
//            val retrofit = Retrofit.Builder()
//                .baseUrl(BuildConfig.BASE_URL_REQRES)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
//                .build()
//            return retrofit.create(ReqresService::class.java)
//        }
//    }
//
//}

private fun provideRetrofit(context: Context, baseUrl: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(provideOkhttpClient(context))
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
}

private fun provideOkhttpClient(context: Context): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(provideHttpLoggingInterceptor())
        .addInterceptor(provideChuckerInterceptor(context))
        .build()
}

private fun provideHttpLoggingInterceptor(): Interceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return httpLoggingInterceptor
}

private fun provideChuckerInterceptor(context: Context): Interceptor {
    return ChuckerInterceptor.Builder(context).collector(provideChuckerCollector(context)).build()
}

private fun provideChuckerCollector(context: Context): ChuckerCollector {
    return ChuckerCollector(context = context, showNotification = true, retentionPeriod = RetentionManager.Period.ONE_HOUR)
}

fun provideLoginService(context: Context): ReqresService {
    return provideRetrofit(
        context,
        BuildConfig.BASE_URL_REQRES,
    ).create(ReqresService::class.java)
}