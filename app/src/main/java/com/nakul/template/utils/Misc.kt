package com.nakul.template.utils

fun safeCall(data: () -> Unit) {
    try {
        data()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}