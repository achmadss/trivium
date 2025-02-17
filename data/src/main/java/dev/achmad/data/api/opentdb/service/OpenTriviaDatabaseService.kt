package dev.achmad.data.api.opentdb.service

import dev.achmad.core.OPEN_TRIVIA_DATABASE_API_PREFIX
import dev.achmad.core.OPEN_TRIVIA_DATABASE_API_TOKEN_PREFIX
import dev.achmad.data.api.opentdb.model.response.session.RequestSessionTokenResponse
import dev.achmad.data.api.opentdb.model.response.session.ResetSessionTokenResponse
import dev.achmad.data.api.opentdb.model.response.trivia.GetTriviaResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenTriviaDatabaseService {

    @GET(OPEN_TRIVIA_DATABASE_API_PREFIX)
    fun getTrivia(
        @Query("amount") amount: Int,
        @Query("category") category: Int,
        @Query("difficulty") difficulty: String,
        @Query("type") type: String,
        @Query("token") token: String,
    ): Response<GetTriviaResponse>

    @GET(OPEN_TRIVIA_DATABASE_API_TOKEN_PREFIX)
    fun getSessionToken(
        @Query("command") command: String = "request",
    ): Response<RequestSessionTokenResponse>

    @GET(OPEN_TRIVIA_DATABASE_API_TOKEN_PREFIX)
    fun resetSessionToken(
        @Query("token") token: String,
        @Query("command") command: String = "reset",
    ): Response<ResetSessionTokenResponse>

}