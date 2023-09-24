package com.nakul.template.network_calling
//
//
//import io.ktor.client.request.get
//import io.ktor.client.request.headers
//import io.ktor.client.request.parameter
//import io.ktor.client.request.post
//import io.ktor.http.HttpHeaders
//
//object ApiInterface {
//    suspend inline fun <reified T> hitPost(
//        endPoint: String,
//        data: Any?,
//        customToken: String? = null
//    ): T {
//        return ApiUtil.getHttpClient().use {
//            it.post("${BuildConfig.BASE_URL}$endPoint") {
//                headers {
//                    val token = customToken ?: SharedPrefUtil.getToken()
//                    if (token.isNotBlank() == true)
//                        append(HttpHeaders.Authorization, token)
//                }
//                if (data != null) {
//                    body = data
//                }
//            }
//        }
//    }
//
//    suspend inline fun <reified T> hitGet(
//        endPoint: String,
//        customToken: String? = null,
//        queries: HashMap<String, Any>? = null,
//    ): T {
//        return ApiUtil.getHttpClient().use {
//            it.get("${BuildConfig.BASE_URL}$endPoint") {
//                headers {
//                    val token = customToken ?: SharedPrefUtil.getToken()
//                    if (token.isNotBlank() == true)
//                        append(HttpHeaders.Authorization, token)
//                }
//                queries?.keys?.forEach { key ->
//                    parameter(key, queries[key])
//                }
//            }
//        }
//    }
//
//}