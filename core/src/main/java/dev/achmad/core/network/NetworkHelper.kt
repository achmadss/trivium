package dev.achmad.core.network

import retrofit2.HttpException
import retrofit2.Response

sealed class APICallResult<out T> {
    data class Success<out Result>(val data: Result) : APICallResult<Result>()
    data class Error(val code: Int, val error: Throwable) : APICallResult<Nothing>()
}

suspend fun <T> await(
    call: suspend () -> Response<T>
): APICallResult<T> {
    try {
        val response = call()
        when {
            response.isSuccessful -> {
                val body = response.body() ?: throw IllegalStateException("Body is null")
                return APICallResult.Success(body)
            }
            else -> {
                return APICallResult.Error(response.code(), HttpException(response))
            }
        }
    } catch (e: Exception) {
        return APICallResult.Error(-1, e)
    }
}
