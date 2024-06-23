package com.jer.ch4_ch5.data.datasource.local

import android.content.Context

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore



val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "dataStore")