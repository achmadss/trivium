package dev.achmad.core.model.mode

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import dev.achmad.core.R

enum class TriviaMode(
    @StringRes val displayName: Int,
    @DrawableRes val icon: Int,
    @StringRes val description: Int,
) {
    CASUAL(R.string.new_game_casual, R.drawable.videogame_asset, R.string.new_game_casual_description),
    TIME_ATTACK(R.string.new_game_time_attack, R.drawable.timer, R.string.new_game_time_attack_description)
}