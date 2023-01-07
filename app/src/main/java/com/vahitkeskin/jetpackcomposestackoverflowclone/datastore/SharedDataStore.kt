package com.vahitkeskin.jetpackcomposestackoverflowclone.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.vahitkeskin.jetpackcomposestackoverflowclone.utils.Contains
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * @authot: Vahit Keskin
 * creared on 5.01.2023
 */
class SharedDataStore(private val context: Context) {

    //Jetpack DataStore & Flow
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
            name = Contains.PACKAGE_NAME
        )
        val SCROLLBAR_DETAIL = booleanPreferencesKey(Contains.DATASTORE_KEY_SCROLLBAR_DETAIL)
        val SWITCH_BUTTON_ZOOM = booleanPreferencesKey(Contains.DATASTORE_KEY_SWITCH_BUTTON_ZOOM)
    }

    //ScroolBar
    val getScrollbarDetail: Flow<Boolean?> = context.dataStore.data
        .map { preferences ->
            preferences[SCROLLBAR_DETAIL] ?: true
        }

    suspend fun saveScrollbarDetail(visible: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[SCROLLBAR_DETAIL] = visible
        }
    }

    //SwitchButton
    val getQuestionsScreenSwitchButtonZoom: Flow<Boolean?> = context.dataStore.data
        .map { preferences ->
            preferences[SWITCH_BUTTON_ZOOM] ?: true
        }

    suspend fun saveQuestionsScreenSwitchButtonZoom(visible: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[SWITCH_BUTTON_ZOOM] = visible
        }
    }

}