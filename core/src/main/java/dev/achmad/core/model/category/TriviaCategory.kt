package dev.achmad.core.model.category

import androidx.annotation.DrawableRes
import dev.achmad.core.R

enum class TriviaCategory(
    val id: Int,
    val displayName: String,
    @DrawableRes val icon: Int,
) {
    RANDOM(-1, "Random", R.drawable.casino),
    GENERAL_KNOWLEDGE(9, "General Knowledge", R.drawable.quiz),
        ENTERTAINMENT_BOOKS(10, "Entertainment: Books", R.drawable.book),
    ENTERTAINMENT_FILM(11, "Entertainment: Film", R.drawable.movie),
    ENTERTAINMENT_MUSIC(12, "Entertainment: Music", R.drawable.genres),
    ENTERTAINMENT_MUSICALS_THEATRES(13, "Entertainment: Musicals & Theatres", R.drawable.artist),
    ENTERTAINMENT_TELEVISION(14, "Entertainment: Television", R.drawable.desktop_windows),
    ENTERTAINMENT_VIDEO_GAMES(15, "Entertainment: Video Games", R.drawable.sports_esports),
    ENTERTAINMENT_BOARD_GAMES(16, "Entertainment: Board Games", R.drawable.chess_pawn),
    SCIENCE_NATURE(17, "Science & Nature", R.drawable.eco),
    SCIENCE_COMPUTERS(18, "Science: Computers", R.drawable.terminal),
    SCIENCE_MATHEMATICS(19, "Science: Mathematics", R.drawable.calculate),
    MYTHOLOGY(20, "Mythology", R.drawable.brightness_7),
    SPORTS(21, "Sports", R.drawable.directions_run),
    GEOGRAPHY(22, "Geography", R.drawable.language),
    HISTORY(23, "History", R.drawable.account_balance),
    POLITICS(24, "Politics", R.drawable.gavel),
    ART(25, "Art", R.drawable.brush),
    CELEBRITIES(26, "Celebrities", R.drawable.groups),
    ANIMALS(27, "Animals", R.drawable.raven),
    VEHICLES(28, "Vehicles", R.drawable.airport_shuttle),
    ENTERTAINMENT_COMICS(29, "Entertainment: Comics", R.drawable.photo_album),
    SCIENCE_GADGETS(30, "Science: Gadgets", R.drawable.devices_other),
    ENTERTAINMENT_JAPANESE_ANIME_MANGA(31, "Entertainment: Japanese Anime & Manga", R.drawable.japanese),
    ENTERTAINMENT_CARTOON_ANIMATIONS(32, "Entertainment: Cartoon & Animations", R.drawable.animation);

    companion object {
        fun fromId(id: Int): TriviaCategory? {
            return entries.find { it.id == id }
        }
    }
}