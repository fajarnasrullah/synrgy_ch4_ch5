package com.jer.ch4_ch5.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.jer.ch4_ch5.data.datasource.LoginLocalSource
import com.jer.ch4_ch5.data.datasource.LoginRemoteSource
import com.jer.ch4_ch5.data.datasource.local.ImplementLoginLocal
import com.jer.ch4_ch5.data.datasource.local.dataStore
import com.jer.ch4_ch5.data.datasource.remote.ImplementLoginRemote
import com.jer.ch4_ch5.data.datasource.remote.retrofit.login.ApiClientLogin
import com.jer.ch4_ch5.data.datasource.remote.retrofit.login.ReqresService
import com.jer.ch4_ch5.data.repository.login.ImplementLoginRepository
import com.jer.ch4_ch5.data.repository.students.NoteStudentsRepository
import com.jer.ch4_ch5.domain.repository.LoginRepository
import com.jer.ch4_ch5.domain.usecase.LoginUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module



val koinModule = module {
    single<LoginUseCase> { LoginUseCase(loginRepository = get()) }
    single<LoginRepository> { ImplementLoginRepository(loginRemote = get(), loginLocal = get()) }
    single<LoginRemoteSource> { ImplementLoginRemote(reqresService = get()) }
    single<ReqresService> {ApiClientLogin.getApiService(get()) }
    single<LoginLocalSource> { ImplementLoginLocal(get()) }
    single<DataStore<Preferences>> { androidContext().dataStore }
//        single<NoteStudentsRepository> { NoteStudentsRepository(Application()) }
    single<NoteStudentsRepository> { NoteStudentsRepository(get()) }



}

