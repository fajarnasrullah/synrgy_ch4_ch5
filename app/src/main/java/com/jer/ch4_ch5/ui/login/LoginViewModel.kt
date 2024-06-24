package com.jer.ch4_ch5.ui.login

import android.content.Context
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.google.gson.Gson
import com.jer.ch4_ch5.data.datasource.local.ImplementLoginLocal
import com.jer.ch4_ch5.data.datasource.local.dataStore
import com.jer.ch4_ch5.data.datasource.remote.ImplementLoginRemote
import com.jer.ch4_ch5.data.datasource.remote.retrofit.login.ApiClientLogin
import com.jer.ch4_ch5.data.repository.login.ImplementLoginRepository

import com.jer.ch4_ch5.domain.repository.LoginRepository
import com.jer.ch4_ch5.domain.usecase.LoginUseCase

import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(
//    private val context: Context,

    private val loginUseCase: LoginUseCase,
//    private val loginRepository: LoginRepository
): ViewModel() {


//    companion object {
//        fun provideFactory(
//            owner: SavedStateRegistryOwner,
//            context: Context,
//        ): AbstractSavedStateViewModelFactory =
//            object : AbstractSavedStateViewModelFactory(owner, null) {
//                @Suppress("UNCHECKED_CAST")
//                override fun <T : ViewModel> create(
//                    key: String,
//                    modelClass: Class<T>,
//                    handle: SavedStateHandle,
//                ): T {
//                    val loginRepository: com.jer.ch4_ch5.domain.repository.LoginRepository = ImplementLoginRepository(
//                            loginLocal = ImplementLoginLocal(
////                                sharedPreferences = SharedPreferencesFactory().createSharedPreferences(context),
//                                dataStore = context.dataStore
//                            ),
//                    loginRemote = ImplementLoginRemote(reqresService = ApiClientLogin.getApiService(context)),
//                    )
//                    return LoginViewModel(  loginUseCase = com.jer.ch4_ch5.domain.usecase.LoginUseCase(
//                        loginRepository
//                    )
//                    ) as T
//                }
//            }
//    }


    private val _works = MutableLiveData<Boolean>()
    val works: LiveData<Boolean> = _works
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun login(username: String, password: String) {
//        val user = UserNote()
//        val userPreferences = UserPreferences(context)

//        user.username = username
//        user.password = password
//        userPreferences.setUser(user)

        viewModelScope.launch {

            try {
                loginUseCase.login(username, password)
//                val token = loginRepository.login(username, password)
//                loginRepository.saveToken(token)
                _works.value = true
            } catch (throwable: Throwable) {
                if (throwable is HttpException) {
                    val json = throwable.response()?.errorBody()?.string()
                    val error = Gson().fromJson(json, ErrorResponse::class.java)
                    _error.value = error.error
                } else {
                    _error.value = throwable.message
                }
            }



        }

    }

}