package dev.achmad.data.api.opentdb.preference

import dev.achmad.core.OPEN_TRIVIA_DATABASE_PREF
import dev.achmad.core.preference.PreferenceStore

class OpenTriviaDatabasePreference(
    private val preferenceStore: PreferenceStore
) {
    fun sessionToken() = preferenceStore.getString(OPEN_TRIVIA_DATABASE_PREF.plus("token"))
}