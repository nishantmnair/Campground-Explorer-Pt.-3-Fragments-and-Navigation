package com.codepath.lab6

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class AlertResponse(
    @SerialName("data")
    val data: List<Alert>?
)

@Keep
@Serializable
data class Alert(
    @SerialName("title")
    val title: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("category")
    val category: String?,
    @SerialName("url")
    val url: String?
) : java.io.Serializable
