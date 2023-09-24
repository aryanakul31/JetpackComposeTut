package com.nakul.template

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainVM : ViewModel() {

    private val _navigateTo = mutableStateOf("")
    val navigateTo: State<String> = _navigateTo

    fun navigateTo(route:String){

    }

}