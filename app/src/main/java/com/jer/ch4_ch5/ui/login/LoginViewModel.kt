package com.jer.ch4_ch5.ui.login

import android.content.Context
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.jer.ch4_ch5.SharedPreferencesFactory
import com.jer.ch4_ch5.data.repository.login.ImplementLoginLocal
import com.jer.ch4_ch5.data.repository.login.ImplementLoginRemote
import com.jer.ch4_ch5.data.repository.login.ImplementLoginRepository
import com.jer.ch4_ch5.data.repository.login.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(
//    private val context: Context,
    private val loginRepository: LoginRepository
): ViewModel() {


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
                                sharedPreferences = SharedPreferencesFactory().createSharedPreferences(context),
                            ),
                    loginRemote = ImplementLoginRemote(),
                    )
                    return LoginViewModel(  loginRepository) as T
                }
            }
    }


    private val _works = MutableLiveData<Boolean>()
    val works: LiveData<Boolean> = _works
    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    fun login(username: String, password: String) {
//        val user = UserNote()
//        val userPreferences = UserPreferences(context)

//        user.username = username
//        user.password = password
//        userPreferences.setUser(user)

        viewModelScope.launch {

            try {
                val token = loginRepository.login(username, password)
                loginRepository.saveToken(token)
                _works.value = true
            } catch (throwable: Throwable) {
                _error.value = throwable
            }



        }

    }

}