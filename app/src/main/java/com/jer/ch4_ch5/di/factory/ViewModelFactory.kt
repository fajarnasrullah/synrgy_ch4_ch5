package com.jer.ch4_ch5.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jer.ch4_ch5.di.Module
import com.jer.ch4_ch5.domain.usecase.LoginUseCase
import com.jer.ch4_ch5.ui.GetAllNotesViewModel
import com.jer.ch4_ch5.ui.NoteViewModel
import com.jer.ch4_ch5.ui.art.ArtViewModel
import com.jer.ch4_ch5.ui.login.GateViewModel
import com.jer.ch4_ch5.ui.login.ItsNavigatorViewmodel
import com.jer.ch4_ch5.ui.login.LoginViewModel
import com.jer.ch4_ch5.ui.login.RegisterViewModel

class ViewModelFactory(private val module: Module): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {

            LoginViewModel::class.java -> LoginViewModel(
                loginUseCase = module.loginUseCase
            ) as T

            RegisterViewModel::class.java -> RegisterViewModel(
                loginRepository = module.loginRepository
            ) as T

//            ArtViewModel::class.java -> ArtViewModel as T

            GateViewModel::class.java -> GateViewModel(
                loginRepository = module.loginRepository
            ) as T

            ItsNavigatorViewmodel::class.java -> ItsNavigatorViewmodel(
                loginRepository = module.loginRepository
            ) as T

            GetAllNotesViewModel::class.java -> GetAllNotesViewModel(
                repository = module.noteStudentsRepository
            ) as T

            NoteViewModel::class.java -> NoteViewModel(
                repository = module.noteStudentsRepository
            ) as T

            else -> {
                throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
            }
        }
    }

}