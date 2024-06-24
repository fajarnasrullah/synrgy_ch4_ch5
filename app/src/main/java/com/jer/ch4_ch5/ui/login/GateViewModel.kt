package com.jer.ch4_ch5.ui.login

import android.content.Context
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.jer.ch4_ch5.data.datasource.local.ImplementLoginLocal
import com.jer.ch4_ch5.data.datasource.local.dataStore
import com.jer.ch4_ch5.data.datasource.remote.ImplementLoginRemote
import com.jer.ch4_ch5.data.datasource.remote.retrofit.login.ApiClientLogin
import com.jer.ch4_ch5.data.repository.login.ImplementLoginRepository
import com.jer.ch4_ch5.domain.repository.LoginRepository
import kotlinx.coroutines.launch

class GateViewModel(private val loginRepository: LoginRepository): ViewModel() {

//    companion object {
//
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
//                        loginLocal = ImplementLoginLocal(
////                                sharedPreferences = SharedPreferencesFactory().createSharedPreferences(context),
//                            dataStore = context.dataStore
//                        ),
//                        loginRemote = ImplementLoginRemote(reqresService = ApiClientLogin.getApiService(context)),
//                    )
//                    return GateViewModel( loginRepository) as T
//                }
//            }
//    }

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
    fun logout() {
        viewModelScope.launch {
            try {
                loginRepository.deleteToken()
            } catch (t: Throwable) {
                _error.value = t.message
            }
        }
    }

}