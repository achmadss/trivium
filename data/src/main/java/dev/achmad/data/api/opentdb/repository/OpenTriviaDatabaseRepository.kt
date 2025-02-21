package dev.achmad.data.api.opentdb.repository

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
        category: TriviaCategory? = null,
        difficulty: TriviaDifficulty? = null,
        type: TriviaType? = null,
    ): APICallResult<GetTriviaResponse> {   

        fun TriviaCategory?.orRandom() = this?.id ?: TriviaCategory.entries.filter { it.id != -1 }.random().id
        fun TriviaDifficulty?.orRandom() = this?.key ?: TriviaDifficulty.entries.random().key
        fun TriviaType?.orRandom() = this?.key ?: TriviaType.entries.random().key

        while (true) {
            val response = await {
                service.getTrivia(
                    amount = amount,
                    category = category.orRandom(),
                    difficulty = difficulty.orRandom(),
                    type = type.orRandom(),
                    token = preference.sessionToken().get()
                )
            }

            when(response) {
                is APICallResult.Success -> {
                    when (response.data.responseCode) {
                        3 -> requestSessionToken() // token expired
                        4 -> resetSessionToken(preference.sessionToken().get()) // reset exhausted token
                        else -> return response
                    }
                }
                is APICallResult.Error -> {
                    return response // network/API errors
                }
            }
        }
    }

}