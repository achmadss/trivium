package dev.achmad.trivium.ui.components.achievement.preference

import dev.achmad.core.TRIVIUM_ACHIEVEMENT_PREF
import dev.achmad.core.preference.PreferenceStore

class TriviumAchievementPreference(
    private val preferenceStore: PreferenceStore
) {

    fun collectedAchievements() = preferenceStore.getStringSet(TRIVIUM_ACHIEVEMENT_PREF.plus("collected"))

}