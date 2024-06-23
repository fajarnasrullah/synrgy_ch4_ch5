package com.jer.ch4_ch5.domain.usecase

class LoginUseCase(private val loginRepository: com.jer.ch4_ch5.domain.repository.LoginRepository) {

    suspend fun login (username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            throw IllegalArgumentException("username or password cannot be empty")
        } else {
            val token = loginRepository.login(username, password)
            loginRepository.saveToken(token)
        }
    }


}