package com.jer.ch4_ch5.data.datasource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.jer.ch4_ch5.data.datasource.LoginLocalSource
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class ImplementLoginLocal(
//    context: Context,
//    private val sharedPreferences: SharedPreferences
    private val dataStore: DataStore<Preferences>
): com.jer.ch4_ch5.data.datasource.LoginLocalSource {

    companion object {
        const val KEY = "token"
        private val DATASTORE_KEY = stringPreferencesKey(KEY)
    }



    override suspend fun saveToken(token: String) {
//        sharedPreferences.edit().putString(KEY, token).apply()
        dataStore.edit {
            it[DATASTORE_KEY] = token
        }
    }

    override suspend fun loadtoken(): String? {
//        return sharedPreferences.getString(KEY, null)
        return dataStore.data.map {
            it[DATASTORE_KEY]
        }.firstOrNull()

    }

    override suspend fun deleteToken() {
//        sharedPreferences.edit().remove(KEY).apply()
        dataStore.edit {
            it[DATASTORE_KEY] = ""
        }
    }
}