package com.jer.ch4_ch5.data.repository.login

class ImplementLoginRepository(
//    context: Context
    private val loginRemote: LoginRemoteSource,
    private val loginLocal: LoginLocalSource,
): LoginRepository {

//    companion object {
//        const val KEY = "token"
//        const val SP = "shared_preferences"
//    }
//
//    private val preferences = context.getSharedPreferences(SP, Context.MODE_PRIVATE)

//    val user = listOf(
//        UserNote(username = "fajar", password = "12345678")
//    )


    override suspend fun login(username: String, password: String): String {
//        val users = user.contains(UserNote(username, password))
//
//
//
//        if (users) {
//            return "qwertyuiopasdfghjklzxcvbnm"
//        }
//        else{
//            throw UnsupportedOperationException("user belum registrasi")
////           Log.e("LoginRemote", "user belum tidak tersedia")
//        }
        return loginRemote.login(username, password)
    }

    override fun saveToken(token: String) {
//        preferences.edit().putString(KEY, token).apply()

        loginLocal.saveToken(token)
    }

    override fun loadToken(): String? {
//        return preferences.getString(KEY, null)

        return loginLocal.loadtoken()
    }

    override fun deleteToken() {
//        preferences.edit().remove(KEY).apply()

        loginLocal.deleteToken()
    }

}