package dev.achmad.trivium.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.achmad.core.preference.PreferenceStore
import dev.achmad.trivium.base.BasePreferences
import dev.achmad.trivium.ui.components.achievement.preference.TriviumAchievementPreference
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TriviumModule {

    @Provides
    @Singleton
    fun provideTriviumPreferences(
        preferenceStore: PreferenceStore
    ) = BasePreferences(preferenceStore)

    @Provides
    @Singleton
    fun provideTriviumAchievementPreferences(
        preferenceStore: PreferenceStore
    ) = TriviumAchievementPreference(preferenceStore)

}