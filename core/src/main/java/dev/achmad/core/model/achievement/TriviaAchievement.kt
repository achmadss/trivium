package dev.achmad.core.model.achievement

import androidx.annotation.StringRes
import dev.achmad.core.R
import dev.achmad.core.model.category.TriviaCategory
import dev.achmad.core.model.difficulty.TriviaDifficulty

enum class TriviaAchievement(
    val id: Int,
    @StringRes val title: Int,
    @StringRes val description: Int,
    val category: String,
    val difficulty: TriviaDifficulty,

) {
    // General Knowledge
    KNOW_IT_ALL_NOVICE(1, R.string.achievement_easy_general_knowledge, R.string.achievement_easy_general_knowledge_desc, "General Knowledge", TriviaDifficulty.EASY),
    SAVVY_SCHOLAR(2, R.string.achievement_medium_general_knowledge, R.string.achievement_medium_general_knowledge_desc, "General Knowledge", TriviaDifficulty.NORMAL),
    RENAISSANCE_MASTER(3, R.string.achievement_hard_general_knowledge, R.string.achievement_hard_general_knowledge_desc, "General Knowledge", TriviaDifficulty.HARD),

    // Books
    BOOKWORM_BEGINNER(4, R.string.achievement_easy_books, R.string.achievement_easy_books_desc, "Books", TriviaDifficulty.EASY),
    LITERARY_EXPLORER(5, R.string.achievement_medium_books, R.string.achievement_medium_books_desc, "Books", TriviaDifficulty.NORMAL),
    MASTER_LIBRARIAN(6, R.string.achievement_hard_books, R.string.achievement_hard_books_desc, "Books", TriviaDifficulty.HARD),

    // Film
    MOVIE_BUFF_ROOKIE(7, R.string.achievement_easy_film, R.string.achievement_easy_film_desc, "Film", TriviaDifficulty.EASY),
    SILVER_SCREEN_ENTHUSIAST(8, R.string.achievement_medium_film, R.string.achievement_medium_film_desc, "Film", TriviaDifficulty.NORMAL),
    CINEMA_CONNOISSEUR(9, R.string.achievement_hard_film, R.string.achievement_hard_film_desc, "Film", TriviaDifficulty.HARD),

    // Music
    RHYTHM_ROOKIE(10, R.string.achievement_easy_music, R.string.achievement_easy_music_desc, "Music", TriviaDifficulty.EASY),
    MELODY_MASTER(11, R.string.achievement_medium_music, R.string.achievement_medium_music_desc, "Music", TriviaDifficulty.NORMAL),
    HARMONY_HERO(12, R.string.achievement_hard_music, R.string.achievement_hard_music_desc, "Music", TriviaDifficulty.HARD),

    // Musicals & Theatres
    STAGE_DOOR_STUDENT(13, R.string.achievement_easy_musicals_theatres, R.string.achievement_easy_musicals_theatres_desc, "Musicals & Theatres", TriviaDifficulty.EASY),
    BROADWAY_ENTHUSIAST(14, R.string.achievement_medium_musicals_theatres, R.string.achievement_medium_musicals_theatres_desc, "Musicals & Theatres", TriviaDifficulty.NORMAL),
    THEATRE_VIRTUOSO(15, R.string.achievement_hard_musicals_theatres, R.string.achievement_hard_musicals_theatres_desc, "Musicals & Theatres", TriviaDifficulty.HARD),

    // Television
    CHANNEL_SURFER(16, R.string.achievement_easy_television, R.string.achievement_easy_television_desc, "Television", TriviaDifficulty.EASY),
    PRIME_TIME_PLAYER(17, R.string.achievement_medium_television, R.string.achievement_medium_television_desc, "Television", TriviaDifficulty.NORMAL),
    TV_TITAN(18, R.string.achievement_hard_television, R.string.achievement_hard_television_desc, "Television", TriviaDifficulty.HARD),

    // Video Games
    PLAYER_ONE_READY(19, R.string.achievement_easy_video_games, R.string.achievement_easy_video_games_desc, "Video Games", TriviaDifficulty.EASY),
    ELITE_GAMER(20, R.string.achievement_medium_video_games, R.string.achievement_medium_video_games_desc, "Video Games", TriviaDifficulty.NORMAL),
    LEGENDARY_PLAYER(21, R.string.achievement_hard_video_games, R.string.achievement_hard_video_games_desc, "Video Games", TriviaDifficulty.HARD),

    // Board Games
    CASUAL_GAMER(22, R.string.achievement_easy_board_games, R.string.achievement_easy_board_games_desc, "Board Games", TriviaDifficulty.EASY),
    STRATEGY_SPECIALIST(23, R.string.achievement_medium_board_games, R.string.achievement_medium_board_games_desc, "Board Games", TriviaDifficulty.NORMAL),
    GRANDMASTER(24, R.string.achievement_hard_board_games, R.string.achievement_hard_board_games_desc, "Board Games", TriviaDifficulty.HARD),

    // Science & Nature
    CURIOUS_EXPLORER(25, R.string.achievement_easy_science_nature, R.string.achievement_easy_science_nature_desc, "Science & Nature", TriviaDifficulty.EASY),
    NATURAL_PHILOSOPHER(26, R.string.achievement_medium_science_nature, R.string.achievement_medium_science_nature_desc, "Science & Nature", TriviaDifficulty.NORMAL),
    SCIENCE_SAGE(27, R.string.achievement_hard_science_nature, R.string.achievement_hard_science_nature_desc, "Science & Nature", TriviaDifficulty.HARD),

    // Computers
    DEBUG_BEGINNER(28, R.string.achievement_easy_computers, R.string.achievement_easy_computers_desc, "Computers", TriviaDifficulty.EASY),
    CODE_WARRIOR(29, R.string.achievement_medium_computers, R.string.achievement_medium_computers_desc, "Computers", TriviaDifficulty.NORMAL),
    TECH_TITAN(30, R.string.achievement_hard_computers, R.string.achievement_hard_computers_desc, "Computers", TriviaDifficulty.HARD),

    // Mathematics
    NUMBER_NOVICE(31, R.string.achievement_easy_mathematics, R.string.achievement_easy_mathematics_desc, "Mathematics", TriviaDifficulty.EASY),
    FORMULA_MASTER(32, R.string.achievement_medium_mathematics, R.string.achievement_medium_mathematics_desc, "Mathematics", TriviaDifficulty.NORMAL),
    MATHEMATICAL_GENIUS(33, R.string.achievement_hard_mathematics, R.string.achievement_hard_mathematics_desc, "Mathematics", TriviaDifficulty.HARD),

    // Mythology
    MYTH_SEEKER(34, R.string.achievement_easy_mythology, R.string.achievement_easy_mythology_desc, "Mythology", TriviaDifficulty.EASY),
    LEGEND_HUNTER(35, R.string.achievement_medium_mythology, R.string.achievement_medium_mythology_desc, "Mythology", TriviaDifficulty.NORMAL),
    MYTHOLOGY_SAGE(36, R.string.achievement_hard_mythology, R.string.achievement_hard_mythology_desc, "Mythology", TriviaDifficulty.HARD),

    // Sports
    ROOKIE_PLAYER(37, R.string.achievement_easy_sports, R.string.achievement_easy_sports_desc, "Sports", TriviaDifficulty.EASY),
    ALL_STAR_ATHLETE(38, R.string.achievement_medium_sports, R.string.achievement_medium_sports_desc, "Sports", TriviaDifficulty.NORMAL),
    SPORTS_LEGEND(39, R.string.achievement_hard_sports, R.string.achievement_hard_sports_desc, "Sports", TriviaDifficulty.HARD),

    // Geography
    GLOBE_TROTTER(40, R.string.achievement_easy_geography, R.string.achievement_easy_geography_desc, "Geography", TriviaDifficulty.EASY),
    WORLD_EXPLORER(41, R.string.achievement_medium_geography, R.string.achievement_medium_geography_desc, "Geography", TriviaDifficulty.NORMAL),
    ATLAS_MASTER(42, R.string.achievement_hard_geography, R.string.achievement_hard_geography_desc, "Geography", TriviaDifficulty.HARD),

    // History
    TIME_TRAVELER_TRAINEE(43, R.string.achievement_easy_history, R.string.achievement_easy_history_desc, "History", TriviaDifficulty.EASY),
    CHRONICLE_KEEPER(44, R.string.achievement_medium_history, R.string.achievement_medium_history_desc, "History", TriviaDifficulty.NORMAL),
    HISTORY_SCHOLAR(45, R.string.achievement_hard_history, R.string.achievement_hard_history_desc, "History", TriviaDifficulty.HARD),

    // Politics
    CIVIC_STUDENT(46, R.string.achievement_easy_politics, R.string.achievement_easy_politics_desc, "Politics", TriviaDifficulty.EASY),
    POLICY_PUNDIT(47, R.string.achievement_medium_politics, R.string.achievement_medium_politics_desc, "Politics", TriviaDifficulty.NORMAL),
    POLITICAL_SAGE(48, R.string.achievement_hard_politics, R.string.achievement_hard_politics_desc, "Politics", TriviaDifficulty.HARD),

    // Art
    ASPIRING_ARTIST(49, R.string.achievement_easy_art, R.string.achievement_easy_art_desc, "Art", TriviaDifficulty.EASY),
    GALLERY_GUIDE(50, R.string.achievement_medium_art, R.string.achievement_medium_art_desc, "Art", TriviaDifficulty.NORMAL),
    ART_CONNOISSEUR(51, R.string.achievement_hard_art, R.string.achievement_hard_art_desc, "Art", TriviaDifficulty.HARD),

    // Celebrities
    FAN_FAVORITE(52, R.string.achievement_easy_celebrities, R.string.achievement_easy_celebrities_desc, "Celebrities", TriviaDifficulty.EASY),
    STAR_TRACKER(53, R.string.achievement_medium_celebrities, R.string.achievement_medium_celebrities_desc, "Celebrities", TriviaDifficulty.NORMAL),
    CELEBRITY_EXPERT(54, R.string.achievement_hard_celebrities, R.string.achievement_hard_celebrities_desc, "Celebrities", TriviaDifficulty.HARD),

    // Animals
    ANIMAL_FRIEND(55, R.string.achievement_easy_animals, R.string.achievement_easy_animals_desc, "Animals", TriviaDifficulty.EASY),
    WILDLIFE_RANGER(56, R.string.achievement_medium_animals, R.string.achievement_medium_animals_desc, "Animals", TriviaDifficulty.NORMAL),
    ZOOLOGY_EXPERT(57, R.string.achievement_hard_animals, R.string.achievement_hard_animals_desc, "Animals", TriviaDifficulty.HARD),

    // Vehicles
    STUDENT_DRIVER(58, R.string.achievement_easy_vehicles, R.string.achievement_easy_vehicles_desc, "Vehicles", TriviaDifficulty.EASY),
    GEAR_HEAD(59, R.string.achievement_medium_vehicles, R.string.achievement_medium_vehicles_desc, "Vehicles", TriviaDifficulty.NORMAL),
    MOTOR_MASTER(60, R.string.achievement_hard_vehicles, R.string.achievement_hard_vehicles_desc, "Vehicles", TriviaDifficulty.HARD),

    // Comics
    COMIC_ROOKIE(61, R.string.achievement_easy_comics, R.string.achievement_easy_comics_desc, "Comics", TriviaDifficulty.EASY),
    PANEL_PRO(62, R.string.achievement_medium_comics, R.string.achievement_medium_comics_desc, "Comics", TriviaDifficulty.NORMAL),
    COMICS_LEGEND(63, R.string.achievement_hard_comics, R.string.achievement_hard_comics_desc, "Comics", TriviaDifficulty.HARD),

    // Gadgets
    TECH_TRAINEE(64, R.string.achievement_easy_gadgets, R.string.achievement_easy_gadgets_desc, "Gadgets", TriviaDifficulty.EASY),
    DEVICE_GURU(65, R.string.achievement_medium_gadgets, R.string.achievement_medium_gadgets_desc, "Gadgets", TriviaDifficulty.NORMAL),
    GADGET_GENIUS(66, R.string.achievement_hard_gadgets, R.string.achievement_hard_gadgets_desc, "Gadgets", TriviaDifficulty.HARD),

    // Japanese Anime & Manga
    ANIME_APPRENTICE(67, R.string.achievement_easy_anime_manga, R.string.achievement_easy_anime_manga_desc, "Japanese Anime & Manga", TriviaDifficulty.EASY),
    MANGA_MASTER(68, R.string.achievement_medium_anime_manga, R.string.achievement_medium_anime_manga_desc, "Japanese Anime & Manga", TriviaDifficulty.NORMAL),
    OTAKU_ORACLE(69, R.string.achievement_hard_anime_manga, R.string.achievement_hard_anime_manga_desc, "Japanese Anime & Manga", TriviaDifficulty.HARD),

    // Cartoon & Animations
    TOON_TRAINEE(70, R.string.achievement_easy_cartoons, R.string.achievement_easy_cartoons_desc, "Cartoon & Animations", TriviaDifficulty.EASY),
    ANIMATION_ACE(71, R.string.achievement_medium_cartoons, R.string.achievement_medium_cartoons_desc, "Cartoon & Animations", TriviaDifficulty.NORMAL),
    CARTOON_CHAMPION(72, R.string.achievement_hard_cartoons, R.string.achievement_hard_cartoons_desc, "Cartoon & Animations", TriviaDifficulty.HARD);


    companion object {
        fun getByCategory(category: String): List<TriviaAchievement> =
            entries.filter { it.category == category }

//        fun getByDifficulty(difficulty: Difficulty): List<TriviaAchievement> =
//            entries.filter { it.difficulty == difficulty }

        fun getByArgs(category: String, difficulty: TriviaDifficulty): List<TriviaAchievement> =
            TriviaAchievement.entries.filter { it.difficulty == difficulty && it.category == category }

        fun getById(id: Int) = entries.find { it.id == id }


    }
}