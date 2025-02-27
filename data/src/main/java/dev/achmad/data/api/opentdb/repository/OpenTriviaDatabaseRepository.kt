package dev.achmad.data.api.opentdb.repository

import dev.achmad.core.OPEN_TRIVIA_DATABASE_RATE_LIMITED
import dev.achmad.core.OPEN_TRIVIA_DATABASE_TOKEN_EXHAUSTED
import dev.achmad.core.OPEN_TRIVIA_DATABASE_TOKEN_EXPIRED
import dev.achmad.core.network.APICallResult
import dev.achmad.core.network.await
import dev.achmad.core.model.category.TriviaCategory
import dev.achmad.core.model.difficulty.TriviaDifficulty
import dev.achmad.data.api.opentdb.model.response.session.RequestSessionTokenResponse
import dev.achmad.data.api.opentdb.model.response.session.ResetSessionTokenResponse
import dev.achmad.data.api.opentdb.model.response.trivia.GetTriviaResponse
import dev.achmad.core.model.type.TriviaType
import dev.achmad.data.api.opentdb.preference.OpenTriviaDatabasePreference
import dev.achmad.data.api.opentdb.service.OpenTriviaDatabaseService
import kotlinx.coroutines.delay

class OpenTriviaDatabaseRepository(
    private val service: OpenTriviaDatabaseService,
    private val preference: OpenTriviaDatabasePreference,
) {
    suspend fun requestSessionToken(): APICallResult<RequestSessionTokenResponse> {
        val result = await { service.getSessionToken() }
        if (result is APICallResult.Success) {
            preference.sessionToken().set(result.data.token)
        }
        return result
    }

    suspend fun resetSessionToken(token: String): APICallResult<ResetSessionTokenResponse> {
        val result = await { service.resetSessionToken(token) }
        if (result is APICallResult.Success) {
            preference.sessionToken().set(result.data.token)
        }
        return result
    }

    suspend fun getTrivia(
        amount: Int,
        category: TriviaCategory,
        difficulty: TriviaDifficulty,
        type: TriviaType,
    ): APICallResult<GetTriviaResponse> {   

        fun TriviaCategory.orRandom() : Int {
            if (this.id == -1) {
                return TriviaCategory.entries.filter { it.id != -1 }.random().id
            }
            return this.id
        }

        while (true) {
            val timeDelay = System.currentTimeMillis() - preference.latestAPICallTimeStamp().get()
            if (timeDelay > 5000) {
                preference.latestAPICallTimeStamp().set(-1L)
                val response = await {
                    service.getTrivia(
                        amount = amount,
                        category = category.orRandom(),
                        difficulty = difficulty.key,
                        type = type.key,
                        token = preference.sessionToken().get()
                    )
                }

                when(response) {
                    is APICallResult.Success -> {
                        when (response.data.responseCode) {
                            OPEN_TRIVIA_DATABASE_TOKEN_EXPIRED -> {
                                requestSessionToken() // token expired
                            }
                            OPEN_TRIVIA_DATABASE_TOKEN_EXHAUSTED -> {
                                resetSessionToken(preference.sessionToken().get()) // reset exhausted token
                            }
                            OPEN_TRIVIA_DATABASE_RATE_LIMITED -> return APICallResult.Error(429, Exception("Too Many Request"))
                            else -> {
                                preference.latestAPICallTimeStamp().set(System.currentTimeMillis())
                                return response
                            }
                        }
                    }
                    is APICallResult.Error -> {
                        preference.latestAPICallTimeStamp().set(System.currentTimeMillis())
                        return response // network/API errors
                    }
                }
            } else {
                delay(5000 - timeDelay)
            }
        }
    }

}