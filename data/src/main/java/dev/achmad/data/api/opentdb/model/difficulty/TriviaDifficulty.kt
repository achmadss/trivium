package dev.achmad.data.api.opentdb.model.difficulty

enum class TriviaDifficulty(val key: String, val displayName: String) {
    EASY("easy", "EASY"),
    NORMAL("medium", "NORMAL"),
    HARD("hard", "HARD"),
}