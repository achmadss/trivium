package dev.achmad.core.network

import androidx.annotation.Keep
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import java.io.IOException

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
                val errorBody = response.errorBody()?.string() ?: throw IllegalStateException("Error body is null")
                val error = Gson().fromJson(errorBody, ErrorBody::class.java)
                return APICallResult.Error(response.code(), IOException(error.errorMessage))
            }
        }
    } catch (e: Exception) {
        return APICallResult.Error(-1, e)
    }
}

@Keep
data class ErrorBody(
    @SerializedName("error") val errorMessage: String,
    @SerializedName("code") val code: Int,
)
