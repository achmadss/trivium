package dev.achmad.data.api.opentdb.model.response.session

import com.google.gson.annotations.SerializedName

data class ResetSessionTokenResponse(
    @SerializedName("response_code") val responseCode: Int,
    @SerializedName("token") val token: String,
)
