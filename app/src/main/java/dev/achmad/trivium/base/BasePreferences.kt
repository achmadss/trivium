package dev.achmad.trivium.base

import dev.achmad.core.TRIVIUM_PREF
import dev.achmad.core.preference.PreferenceStore
import dev.achmad.core.preference.getEnum

class BasePreferences(
    private val preferenceStore: PreferenceStore,
) {
    fun appTheme() = preferenceStore.getEnum(TRIVIUM_PREF.plus("app_theme"), AppTheme.SYSTEM)
    fun dynamicColors() = preferenceStore.getBoolean(TRIVIUM_PREF.plus("dynamic_colors"), true)
}

enum class AppTheme {
    SYSTEM, DARK, LIGHT
}