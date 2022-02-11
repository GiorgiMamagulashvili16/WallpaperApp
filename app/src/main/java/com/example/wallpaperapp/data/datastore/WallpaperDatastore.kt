package com.example.wallpaperapp.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class WallpaperDatastore(private val ctx: Context) {
    private val Context.datastore by preferencesDataStore(name = "wallpaper_datastore")

    suspend fun setIntPreference(key: String, value: Int) {
        val preferenceKey = intPreferencesKey(key)
        ctx.datastore.edit { mutablePreferences ->
            mutablePreferences[preferenceKey] = value
        }
    }

    suspend fun getIntPreference(key: String): Int? {
        val preferenceKey = intPreferencesKey(key)
        return ctx.datastore.data.first()[preferenceKey]
    }
}