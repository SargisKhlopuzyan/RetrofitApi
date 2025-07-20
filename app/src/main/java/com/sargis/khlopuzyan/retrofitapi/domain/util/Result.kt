package com.sargis.khlopuzyan.retrofitapi.domain.util

sealed class Result<T>(
    val data: T? = null,
    val uiError: UiError? = null
) {
    class Success<T>(data: T?) : Result<T>(data)
    class Error<T>(uiError: UiError, data: T? = null) : Result<T>(data, uiError)
}