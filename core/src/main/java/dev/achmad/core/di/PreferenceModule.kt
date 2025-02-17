package dev.achmad.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.achmad.core.preference.PreferenceStore
import dev.achmad.core.preference.PreferenceStoreImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {

    @Provides
    @Singleton
    fun providePreferenceStore(
        @ApplicationContext context: Context,
    ): PreferenceStore = PreferenceStoreImpl(context)

}