package com.codepath.lab6

import kotlinx.serialization.json.Json

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}
