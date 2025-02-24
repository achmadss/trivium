package dev.achmad.core

// Shared Preferences
const val SHARED_PREFERENCES_NAME = "trivium"
const val TRIVIUM_PREF = "trivium_pref_"
const val OPEN_TRIVIA_DATABASE_PREF = "opentdb_pref_"
const val NETWORK_PREF = "network_pref_"
const val TRIVIUM_ACHIEVEMENT_PREF = "trivium_achievement_pref_"

// Hosts
const val API_HOST_TRIVIUM = "api-trivium.achmad.dev"
const val API_HOST_OPEN_TRIVIA_DATABASE = "opentdb.com"

// Retrofit prefix
const val OPEN_TRIVIA_DATABASE_API_PREFIX = "api.php"
const val OPEN_TRIVIA_DATABASE_API_TOKEN_PREFIX = "api_token.php"

// Notification
const val CHANNEL_ID = "trivium_app_notification_channel"
const val NOTIFICATION_ID = 100
const val TRIVIUM_ACTION = "TRIVIUM_ACTION"

// Messages
const val unknownError = "Unknown Error"

// OpenTDB Error Codes
const val OPEN_TRIVIA_DATABASE_TOKEN_EXPIRED = 3
const val OPEN_TRIVIA_DATABASE_TOKEN_EXHAUSTED = 4
const val OPEN_TRIVIA_DATABASE_RATE_LIMITED = 5