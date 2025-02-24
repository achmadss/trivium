package dev.achmad.data.api.opentdb.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.achmad.core.API_HOST_OPEN_TRIVIA_DATABASE
import dev.achmad.core.API_HOST_TRIVIUM
import dev.achmad.core.preference.PreferenceStore
import dev.achmad.data.api.opentdb.preference.OpenTriviaDatabasePreference
import dev.achmad.data.api.opentdb.repository.OpenTriviaDatabaseRepository
import dev.achmad.data.api.opentdb.service.OpenTriviaDatabaseService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OpenTriviaDatabaseModule {

    @Provides
    @Singleton
    fun provideOpenTriviaDatabaseRepository(
        service: OpenTriviaDatabaseService,
        openTriviaDatabasePreference: OpenTriviaDatabasePreference,
    ) = OpenTriviaDatabaseRepository(service, openTriviaDatabasePreference)

    @Provides
    @Singleton
    fun provideOpenTriviaDatabaseService(
        okHttpClient: OkHttpClient
    ): OpenTriviaDatabaseService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://$API_HOST_OPEN_TRIVIA_DATABASE/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
        return retrofit.create(OpenTriviaDatabaseService::class.java)
    }

    @Provides
    @Singleton
    fun provideOpenTriviaDatabasePreference(
        preferenceStore: PreferenceStore
    ) = OpenTriviaDatabasePreference(preferenceStore)

}