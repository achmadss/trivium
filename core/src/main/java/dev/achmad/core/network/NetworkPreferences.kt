package dev.achmad.core.network

import dev.achmad.core.NETWORK_PREF
import dev.achmad.core.preference.PreferenceStore

class NetworkPreferences(
    private val preferenceStore: PreferenceStore
) {
    private val prefix: String = NETWORK_PREF

}