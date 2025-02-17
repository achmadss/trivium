package dev.achmad.data.api.opentdb.model.response.session

import com.google.gson.annotations.SerializedName

data class RequestSessionTokenResponse(
    @SerializedName("response_code") val responseCode: Int,
    @SerializedName("response_message") val responseMessage: String,
    @SerializedName("token") val token: String,
)
