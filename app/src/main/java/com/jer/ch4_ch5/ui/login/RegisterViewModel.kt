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
import com.jer.ch4_ch5.data.repository.login.ImplementLoginLocal
import com.jer.ch4_ch5.data.repository.login.ImplementLoginRemote
import com.jer.ch4_ch5.data.repository.login.ImplementLoginRepository
import com.jer.ch4_ch5.data.repository.login.LoginRepository
import com.jer.ch4_ch5.data.repository.login.dataStore
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterViewModel(private val loginRepository: LoginRepository): ViewModel() {

    companion object {
        fun provideFactory(
            owner: SavedStateRegistryOwner,
            context: Context,
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, null) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle,
                ): T {
                    val loginRepository: LoginRepository = ImplementLoginRepository(
                        loginLocal = ImplementLoginLocal(
//                                sharedPreferences = SharedPreferencesFactory().createSharedPreferences(context),
                            dataStore = context.dataStore
                        ),
                        loginRemote = ImplementLoginRemote(reqresService = ApiClientLogin.getApiService(context)),
                    )
                    return RegisterViewModel(  loginRepository) as T
                }
            }
    }

    private val _token = MutableLiveData<String>()
     val token: LiveData<String> = _token

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error


    fun register(email: String, username: String, password: String) {
        viewModelScope.launch {
            try {
                _token.value = loginRepository.register(email,username, password)
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