package com.jer.ch4_ch5.di.factory

import com.jer.ch4_ch5.ui.GetAllNotesViewModel
import com.jer.ch4_ch5.ui.NoteViewModel
import com.jer.ch4_ch5.ui.login.GateViewModel
import com.jer.ch4_ch5.ui.login.ItsNavigatorViewmodel
import com.jer.ch4_ch5.ui.login.LoginViewModel
import com.jer.ch4_ch5.ui.login.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinViewModelModule = module {

    viewModel { LoginViewModel(loginUseCase = get()) }
    viewModel { RegisterViewModel( loginRepository = get()) }
    viewModel { GateViewModel(loginRepository = get()) }
    viewModel { ItsNavigatorViewmodel(loginRepository = get()) }
    viewModel { GetAllNotesViewModel(repository = get()) }
    viewModel { NoteViewModel(repository = get()) }

}