package com.jer.ch4_ch5.ui.login

import android.content.Context
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.jer.ch4_ch5.data.repository.login.ImplementLoginLocal
import com.jer.ch4_ch5.data.repository.login.ImplementLoginRemote
import com.jer.ch4_ch5.data.repository.login.ImplementLoginRepository
import com.jer.ch4_ch5.data.repository.login.LoginRepository
import com.jer.ch4_ch5.data.repository.login.dataStore
import kotlinx.coroutines.launch

class ItsNavigatorViewmodel(private val loginRepository: LoginRepository): ViewModel() {

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
                    return ItsNavigatorViewmodel(loginRepository) as T
                }
            }
    }


    val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean> = _isLogin

    fun loginCheck() {
        viewModelScope.launch {
            _isLogin.value = !loginRepository.loadToken().isNullOrEmpty()

        }
    }


}