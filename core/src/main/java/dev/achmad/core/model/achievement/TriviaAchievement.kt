package dev.achmad.core.model.achievement

import androidx.annotation.StringRes
import dev.achmad.core.R

enum class TriviaAchievement(
    val id: Int,
    @StringRes val title: Int,
    @StringRes val description: Int,
    val category: String,
    val difficulty: Difficulty,

) {
    // General Knowledge
    KNOW_IT_ALL_NOVICE(1, R.string.achievement_easy_general_knowledge, R.string.achievement_easy_general_knowledge_desc, "General Knowledge", Difficulty.EASY),
    SAVVY_SCHOLAR(2, R.string.achievement_medium_general_knowledge, R.string.achievement_medium_general_knowledge_desc, "General Knowledge", Difficulty.MEDIUM),
    RENAISSANCE_MASTER(3, R.string.achievement_hard_general_knowledge, R.string.achievement_hard_general_knowledge_desc, "General Knowledge", Difficulty.HARD),

    // Books
    BOOKWORM_BEGINNER(4, R.string.achievement_easy_books, R.string.achievement_easy_books_desc, "Books", Difficulty.EASY),
    LITERARY_EXPLORER(5, R.string.achievement_medium_books, R.string.achievement_medium_books_desc, "Books", Difficulty.MEDIUM),
    MASTER_LIBRARIAN(6, R.string.achievement_hard_books, R.string.achievement_hard_books_desc, "Books", Difficulty.HARD),

    // Film
    MOVIE_BUFF_ROOKIE(7, R.string.achievement_easy_film, R.string.achievement_easy_film_desc, "Film", Difficulty.EASY),
    SILVER_SCREEN_ENTHUSIAST(8, R.string.achievement_medium_film, R.string.achievement_medium_film_desc, "Film", Difficulty.MEDIUM),
    CINEMA_CONNOISSEUR(9, R.string.achievement_hard_film, R.string.achievement_hard_film_desc, "Film", Difficulty.HARD),

    // Music
    RHYTHM_ROOKIE(10, R.string.achievement_easy_music, R.string.achievement_easy_music_desc, "Music", Difficulty.EASY),
    MELODY_MASTER(11, R.string.achievement_medium_music, R.string.achievement_medium_music_desc, "Music", Difficulty.MEDIUM),
    HARMONY_HERO(12, R.string.achievement_hard_music, R.string.achievement_hard_music_desc, "Music", Difficulty.HARD),

    // Musicals & Theatres
    STAGE_DOOR_STUDENT(13, R.string.achievement_easy_musicals_theatres, R.string.achievement_easy_musicals_theatres_desc, "Musicals & Theatres", Difficulty.EASY),
    BROADWAY_ENTHUSIAST(14, R.string.achievement_medium_musicals_theatres, R.string.achievement_medium_musicals_theatres_desc, "Musicals & Theatres", Difficulty.MEDIUM),
    THEATRE_VIRTUOSO(15, R.string.achievement_hard_musicals_theatres, R.string.achievement_hard_musicals_theatres_desc, "Musicals & Theatres", Difficulty.HARD),

    // Television
    CHANNEL_SURFER(16, R.string.achievement_easy_television, R.string.achievement_easy_television_desc, "Television", Difficulty.EASY),
    PRIME_TIME_PLAYER(17, R.string.achievement_medium_television, R.string.achievement_medium_television_desc, "Television", Difficulty.MEDIUM),
    TV_TITAN(18, R.string.achievement_hard_television, R.string.achievement_hard_television_desc, "Television", Difficulty.HARD),

    // Video Games
    PLAYER_ONE_READY(19, R.string.achievement_easy_video_games, R.string.achievement_easy_video_games_desc, "Video Games", Difficulty.EASY),
    ELITE_GAMER(20, R.string.achievement_medium_video_games, R.string.achievement_medium_video_games_desc, "Video Games", Difficulty.MEDIUM),
    LEGENDARY_PLAYER(21, R.string.achievement_hard_video_games, R.string.achievement_hard_video_games_desc, "Video Games", Difficulty.HARD),

    // Board Games
    CASUAL_GAMER(22, R.string.achievement_easy_board_games, R.string.achievement_easy_board_games_desc, "Board Games", Difficulty.EASY),
    STRATEGY_SPECIALIST(23, R.string.achievement_medium_board_games, R.string.achievement_medium_board_games_desc, "Board Games", Difficulty.MEDIUM),
    GRANDMASTER(24, R.string.achievement_hard_board_games, R.string.achievement_hard_board_games_desc, "Board Games", Difficulty.HARD),

    // Science & Nature
    CURIOUS_EXPLORER(25, R.string.achievement_easy_science_nature, R.string.achievement_easy_science_nature_desc, "Science & Nature", Difficulty.EASY),
    NATURAL_PHILOSOPHER(26, R.string.achievement_medium_science_nature, R.string.achievement_medium_science_nature_desc, "Science & Nature", Difficulty.MEDIUM),
    SCIENCE_SAGE(27, R.string.achievement_hard_science_nature, R.string.achievement_hard_science_nature_desc, "Science & Nature", Difficulty.HARD),

    // Computers
    DEBUG_BEGINNER(28, R.string.achievement_easy_computers, R.string.achievement_easy_computers_desc, "Computers", Difficulty.EASY),
    CODE_WARRIOR(29, R.string.achievement_medium_computers, R.string.achievement_medium_computers_desc, "Computers", Difficulty.MEDIUM),
    TECH_TITAN(30, R.string.achievement_hard_computers, R.string.achievement_hard_computers_desc, "Computers", Difficulty.HARD),

    // Mathematics
    NUMBER_NOVICE(31, R.string.achievement_easy_mathematics, R.string.achievement_easy_mathematics_desc, "Mathematics", Difficulty.EASY),
    FORMULA_MASTER(32, R.string.achievement_medium_mathematics, R.string.achievement_medium_mathematics_desc, "Mathematics", Difficulty.MEDIUM),
    MATHEMATICAL_GENIUS(33, R.string.achievement_hard_mathematics, R.string.achievement_hard_mathematics_desc, "Mathematics", Difficulty.HARD),

    // Mythology
    MYTH_SEEKER(34, R.string.achievement_easy_mythology, R.string.achievement_easy_mythology_desc, "Mythology", Difficulty.EASY),
    LEGEND_HUNTER(35, R.string.achievement_medium_mythology, R.string.achievement_medium_mythology_desc, "Mythology", Difficulty.MEDIUM),
    MYTHOLOGY_SAGE(36, R.string.achievement_hard_mythology, R.string.achievement_hard_mythology_desc, "Mythology", Difficulty.HARD),

    // Sports
    ROOKIE_PLAYER(37, R.string.achievement_easy_sports, R.string.achievement_easy_sports_desc, "Sports", Difficulty.EASY),
    ALL_STAR_ATHLETE(38, R.string.achievement_medium_sports, R.string.achievement_medium_sports_desc, "Sports", Difficulty.MEDIUM),
    SPORTS_LEGEND(39, R.string.achievement_hard_sports, R.string.achievement_hard_sports_desc, "Sports", Difficulty.HARD),

    // Geography
    GLOBE_TROTTER(40, R.string.achievement_easy_geography, R.string.achievement_easy_geography_desc, "Geography", Difficulty.EASY),
    WORLD_EXPLORER(41, R.string.achievement_medium_geography, R.string.achievement_medium_geography_desc, "Geography", Difficulty.MEDIUM),
    ATLAS_MASTER(42, R.string.achievement_hard_geography, R.string.achievement_hard_geography_desc, "Geography", Difficulty.HARD),

    // History
    TIME_TRAVELER_TRAINEE(43, R.string.achievement_easy_history, R.string.achievement_easy_history_desc, "History", Difficulty.EASY),
    CHRONICLE_KEEPER(44, R.string.achievement_medium_history, R.string.achievement_medium_history_desc, "History", Difficulty.MEDIUM),
    HISTORY_SCHOLAR(45, R.string.achievement_hard_history, R.string.achievement_hard_history_desc, "History", Difficulty.HARD),

    // Politics
    CIVIC_STUDENT(46, R.string.achievement_easy_politics, R.string.achievement_easy_politics_desc, "Politics", Difficulty.EASY),
    POLICY_PUNDIT(47, R.string.achievement_medium_politics, R.string.achievement_medium_politics_desc, "Politics", Difficulty.MEDIUM),
    POLITICAL_SAGE(48, R.string.achievement_hard_politics, R.string.achievement_hard_politics_desc, "Politics", Difficulty.HARD),

    // Art
    ASPIRING_ARTIST(49, R.string.achievement_easy_art, R.string.achievement_easy_art_desc, "Art", Difficulty.EASY),
    GALLERY_GUIDE(50, R.string.achievement_medium_art, R.string.achievement_medium_art_desc, "Art", Difficulty.MEDIUM),
    ART_CONNOISSEUR(51, R.string.achievement_hard_art, R.string.achievement_hard_art_desc, "Art", Difficulty.HARD),

    // Celebrities
    FAN_FAVORITE(52, R.string.achievement_easy_celebrities, R.string.achievement_easy_celebrities_desc, "Celebrities", Difficulty.EASY),
    STAR_TRACKER(53, R.string.achievement_medium_celebrities, R.string.achievement_medium_celebrities_desc, "Celebrities", Difficulty.MEDIUM),
    CELEBRITY_EXPERT(54, R.string.achievement_hard_celebrities, R.string.achievement_hard_celebrities_desc, "Celebrities", Difficulty.HARD),

    // Animals
    ANIMAL_FRIEND(55, R.string.achievement_easy_animals, R.string.achievement_easy_animals_desc, "Animals", Difficulty.EASY),
    WILDLIFE_RANGER(56, R.string.achievement_medium_animals, R.string.achievement_medium_animals_desc, "Animals", Difficulty.MEDIUM),
    ZOOLOGY_EXPERT(57, R.string.achievement_hard_animals, R.string.achievement_hard_animals_desc, "Animals", Difficulty.HARD),

    // Vehicles
    STUDENT_DRIVER(58, R.string.achievement_easy_vehicles, R.string.achievement_easy_vehicles_desc, "Vehicles", Difficulty.EASY),
    GEAR_HEAD(59, R.string.achievement_medium_vehicles, R.string.achievement_medium_vehicles_desc, "Vehicles", Difficulty.MEDIUM),
    MOTOR_MASTER(60, R.string.achievement_hard_vehicles, R.string.achievement_hard_vehicles_desc, "Vehicles", Difficulty.HARD),

    // Comics
    COMIC_ROOKIE(61, R.string.achievement_easy_comics, R.string.achievement_easy_comics_desc, "Comics", Difficulty.EASY),
    PANEL_PRO(62, R.string.achievement_medium_comics, R.string.achievement_medium_comics_desc, "Comics", Difficulty.MEDIUM),
    COMICS_LEGEND(63, R.string.achievement_hard_comics, R.string.achievement_hard_comics_desc, "Comics", Difficulty.HARD),

    // Gadgets
    TECH_TRAINEE(64, R.string.achievement_easy_gadgets, R.string.achievement_easy_gadgets_desc, "Gadgets", Difficulty.EASY),
    DEVICE_GURU(65, R.string.achievement_medium_gadgets, R.string.achievement_medium_gadgets_desc, "Gadgets", Difficulty.MEDIUM),
    GADGET_GENIUS(66, R.string.achievement_hard_gadgets, R.string.achievement_hard_gadgets_desc, "Gadgets", Difficulty.HARD),

    // Japanese Anime & Manga
    ANIME_APPRENTICE(67, R.string.achievement_easy_anime_manga, R.string.achievement_easy_anime_manga_desc, "Japanese Anime & Manga", Difficulty.EASY),
    MANGA_MASTER(68, R.string.achievement_medium_anime_manga, R.string.achievement_medium_anime_manga_desc, "Japanese Anime & Manga", Difficulty.MEDIUM),
    OTAKU_ORACLE(69, R.string.achievement_hard_anime_manga, R.string.achievement_hard_anime_manga_desc, "Japanese Anime & Manga", Difficulty.HARD),

    // Cartoon & Animations
    TOON_TRAINEE(70, R.string.achievement_easy_cartoons, R.string.achievement_easy_cartoons_desc, "Cartoon & Animations", Difficulty.EASY),
    ANIMATION_ACE(71, R.string.achievement_medium_cartoons, R.string.achievement_medium_cartoons_desc, "Cartoon & Animations", Difficulty.MEDIUM),
    CARTOON_CHAMPION(72, R.string.achievement_hard_cartoons, R.string.achievement_hard_cartoons_desc, "Cartoon & Animations", Difficulty.HARD);

    enum class Difficulty {
        EASY, MEDIUM, HARD
    }

    companion object {
        fun getByCategory(category: String): List<TriviaAchievement> =
            entries.filter { it.category == category }

        fun getByDifficulty(difficulty: Difficulty): List<TriviaAchievement> =
            entries.filter { it.difficulty == difficulty }

        fun getById(id: Int) = entries.find { it.id == id }
    }
}