package com.nakul.template.database.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object DatastoreKeys {
    const val DATA_STORE_NAME = "ApplicationTemplate"
    val LOGIN_DATA by lazy { stringPreferencesKey("LOGIN_DATA") }
    val TOKEN by lazy { stringPreferencesKey("TOKEN_nidifi") }
}
