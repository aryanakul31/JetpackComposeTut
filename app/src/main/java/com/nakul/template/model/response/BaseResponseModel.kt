package com.nakul.template.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponseModel<T>(
    @SerialName("data")
    var data: T? = null,
    @SerialName("message")
    var message: String? = null,
    @SerialName("statusCode")
    var statusCode: Int? = null
)