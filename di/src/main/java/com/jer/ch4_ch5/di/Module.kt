package com.jer.ch4_ch5.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.jer.ch4_ch5.data.datasource.LoginLocalSource
import com.jer.ch4_ch5.data.datasource.LoginRemoteSource
import com.jer.ch4_ch5.data.datasource.local.ImplementLoginLocal
import com.jer.ch4_ch5.data.datasource.local.dataStore
import com.jer.ch4_ch5.data.datasource.remote.ImplementLoginRemote
import com.jer.ch4_ch5.data.datasource.remote.retrofit.art.ApiService

import com.jer.ch4_ch5.data.datasource.remote.retrofit.login.ReqresService
import com.jer.ch4_ch5.data.datasource.remote.retrofit.login.provideLoginService
import com.jer.ch4_ch5.data.repository.login.ImplementLoginRepository
import com.jer.ch4_ch5.data.repository.students.NoteStudentsRepository
import com.jer.ch4_ch5.domain.repository.LoginRepository
import com.jer.ch4_ch5.domain.usecase.LoginUseCase

class Module(context: Context) {

    val loginUseCase: LoginUseCase by lazy {
        LoginUseCase(loginRepository = loginRepository)
    }

    val loginRepository: LoginRepository by lazy {
        ImplementLoginRepository(loginRemote = loginRemote, loginLocal = loginLocal)
    }

    val loginRemote: LoginRemoteSource by lazy {
        ImplementLoginRemote(reqresService = reqresService)
    }

    val reqresService: ReqresService by lazy {
        provideLoginService(context)
//        ApiClientLogin.getApiService(context)
    }

//    val artService: ApiService by lazy {
//        provideArtService(context)
//    }

    val loginLocal: LoginLocalSource by lazy {
        ImplementLoginLocal(dataStore)
    }

    val dataStore: DataStore<Preferences> by lazy {
        context.dataStore
    }

    val noteStudentsRepository: NoteStudentsRepository by lazy {
        NoteStudentsRepository(Application())
    }
}