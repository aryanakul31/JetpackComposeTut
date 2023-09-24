package com.nakul.template.network_calling

fun interface RequestHandler<T> {
    suspend fun sendRequest(): T
}