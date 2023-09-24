package com.nakul.template.ui.presentation.login

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nakul.template.database.preference.PreferenceImpl
import com.nakul.template.database.preference.PreferenceKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(val preference: PreferenceImpl) : ViewModel() {
    val email = mutableStateOf("")

    val emailError = derivedStateOf {
        if (email.value.isNotBlank()) ""
        else "Email cannot be empty"
    }

    val password = mutableStateOf("")
    val passwordError = derivedStateOf {
        if (password.value.isNotBlank()) ""
        else "Password cannot be empty"
    }

    val rememberMe = mutableStateOf(true)

    fun hitLogin() = viewModelScope.launch {
        if (emailError.value.isNotBlank() || passwordError.value.isNotBlank()) return@launch


    }

    fun handleRemeberMe() {
        preference.storeKey(PreferenceKeys.EMAIL, email.value)
        preference.storeKey(PreferenceKeys.PASSWORD, password.value)
    }
}
