package dev.achmad.core.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.achmad.core.HILT_RETROFIT_TRIVIUM
import dev.achmad.core.HILT_RETROFIT_OPEN_TRIVIA_DATABASE
import dev.achmad.core.API_HOST_TRIVIUM
import dev.achmad.core.API_HOST_OPEN_TRIVIA_DATABASE
import dev.achmad.core.preference.PreferenceStore
import dev.achmad.core.network.NetworkPreferences
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkPreferences(
        preferenceStore: PreferenceStore
    ) = NetworkPreferences(preferenceStore)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor(ChuckerInterceptor(context))
            .build()
    }

}