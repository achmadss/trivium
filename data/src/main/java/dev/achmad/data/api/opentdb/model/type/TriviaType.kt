package dev.achmad.data.api.opentdb.model.type

enum class TriviaType(val key: String, val displayName: String) {
    MULTIPLE_CHOICE("multiple", "Multiple Choice"),
    TRUE_FALSE("boolean", "True/False"),
}