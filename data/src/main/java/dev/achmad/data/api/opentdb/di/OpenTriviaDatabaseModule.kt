package dev.achmad.data.api.opentdb.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.achmad.core.API_HOST_OPEN_TRIVIA_DATABASE
import dev.achmad.core.API_HOST_TRIVIUM
import dev.achmad.core.HILT_RETROFIT_OPEN_TRIVIA_DATABASE
import dev.achmad.core.HILT_RETROFIT_TRIVIUM
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
    @Named(HILT_RETROFIT_OPEN_TRIVIA_DATABASE)
    fun provideOpenTriviaDatabaseRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://$API_HOST_OPEN_TRIVIA_DATABASE/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenTriviaDatabaseRepository(
        service: OpenTriviaDatabaseService,
        openTriviaDatabasePreference: OpenTriviaDatabasePreference,
    ) = OpenTriviaDatabaseRepository(service, openTriviaDatabasePreference)

    @Provides
    @Singleton
    fun provideOpenTriviaDatabaseService(
        @Named(HILT_RETROFIT_OPEN_TRIVIA_DATABASE) retrofit: Retrofit
    ) = retrofit.create(OpenTriviaDatabaseService::class.java)

    @Provides
    @Singleton
    fun provideOpenTriviaDatabasePreference(
        preferenceStore: PreferenceStore
    ) = OpenTriviaDatabasePreference(preferenceStore)

}