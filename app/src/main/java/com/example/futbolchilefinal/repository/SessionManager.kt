package com.example.futbolchilefinal.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.futbolchilefinal.viewmodel.UserInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

class SessionManager(context: Context) {

    private val dataStore = context.dataStore

    companion object {
        private val USER_NAME_KEY = stringPreferencesKey("user_name")
        private val USER_TEAM_KEY = stringPreferencesKey("user_team")
        private val USER_TEAM_LOGO_KEY = stringPreferencesKey("user_team_logo")
    }

    suspend fun saveSession(userInfo: UserInfo) {
        dataStore.edit {
            it[USER_NAME_KEY] = userInfo.name
            it[USER_TEAM_KEY] = userInfo.favoriteTeam
            it[USER_TEAM_LOGO_KEY] = userInfo.favoriteTeamLogo
        }
    }

    suspend fun clearSession() {
        dataStore.edit {
            it.clear()
        }
    }

    val sessionFlow: Flow<UserInfo?> = dataStore.data.map {
        val name = it[USER_NAME_KEY]
        val team = it[USER_TEAM_KEY]
        val logo = it[USER_TEAM_LOGO_KEY]
        if (name != null && team != null && logo != null) {
            UserInfo(name, team, logo)
        } else {
            null
        }
    }
}
