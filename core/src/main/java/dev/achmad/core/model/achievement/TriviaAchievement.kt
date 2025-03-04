package dev.achmad.core.model.achievement

import androidx.annotation.StringRes
import dev.achmad.core.R
import dev.achmad.core.model.category.TriviaCategory
import dev.achmad.core.model.difficulty.TriviaDifficulty
import dev.achmad.core.model.mode.TriviaMode

enum class TriviaAchievement(
    val id: Int,
    @StringRes val title: Int,
    @StringRes val description: Int,
    val category: TriviaCategory,
    val difficulty: TriviaDifficulty,
    val mode: TriviaMode
, ) {
    // Casual Mode Achievements //
    // General Knowledge
    KNOW_IT_ALL_NOVICE(1, R.string.achievement_easy_general_knowledge, R.string.achievement_easy_general_knowledge_desc, TriviaCategory.GENERAL_KNOWLEDGE, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    SAVVY_SCHOLAR(2, R.string.achievement_medium_general_knowledge, R.string.achievement_medium_general_knowledge_desc, TriviaCategory.GENERAL_KNOWLEDGE, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    RENAISSANCE_MASTER(3, R.string.achievement_hard_general_knowledge, R.string.achievement_hard_general_knowledge_desc, TriviaCategory.GENERAL_KNOWLEDGE, TriviaDifficulty.HARD, TriviaMode.CASUAL),

    // Books
    BOOKWORM_BEGINNER(4, R.string.achievement_easy_books, R.string.achievement_easy_books_desc, TriviaCategory.ENTERTAINMENT_BOOKS, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    LITERARY_EXPLORER(5, R.string.achievement_medium_books, R.string.achievement_medium_books_desc, TriviaCategory.ENTERTAINMENT_BOOKS, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    MASTER_LIBRARIAN(6, R.string.achievement_hard_books, R.string.achievement_hard_books_desc, TriviaCategory.ENTERTAINMENT_BOOKS, TriviaDifficulty.HARD, TriviaMode.CASUAL),

    // Film
    MOVIE_BUFF_ROOKIE(7, R.string.achievement_easy_film, R.string.achievement_easy_film_desc, TriviaCategory.ENTERTAINMENT_FILM, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    SILVER_SCREEN_ENTHUSIAST(8, R.string.achievement_medium_film, R.string.achievement_medium_film_desc, TriviaCategory.ENTERTAINMENT_FILM, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    CINEMA_CONNOISSEUR(9, R.string.achievement_hard_film, R.string.achievement_hard_film_desc, TriviaCategory.ENTERTAINMENT_FILM, TriviaDifficulty.HARD, TriviaMode.CASUAL),

    // Music
    RHYTHM_ROOKIE(10, R.string.achievement_easy_music, R.string.achievement_easy_music_desc, TriviaCategory.ENTERTAINMENT_MUSIC, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    MELODY_MASTER(11, R.string.achievement_medium_music, R.string.achievement_medium_music_desc, TriviaCategory.ENTERTAINMENT_MUSIC, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    HARMONY_HERO(12, R.string.achievement_hard_music, R.string.achievement_hard_music_desc, TriviaCategory.ENTERTAINMENT_MUSIC, TriviaDifficulty.HARD, TriviaMode.CASUAL),

    // Musicals & Theatres
    STAGE_DOOR_STUDENT(13, R.string.achievement_easy_musicals_theatres, R.string.achievement_easy_musicals_theatres_desc, TriviaCategory.ENTERTAINMENT_MUSICALS_THEATRES, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    BROADWAY_ENTHUSIAST(14, R.string.achievement_medium_musicals_theatres, R.string.achievement_medium_musicals_theatres_desc, TriviaCategory.ENTERTAINMENT_MUSICALS_THEATRES, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    THEATRE_VIRTUOSO(15, R.string.achievement_hard_musicals_theatres, R.string.achievement_hard_musicals_theatres_desc, TriviaCategory.ENTERTAINMENT_MUSICALS_THEATRES, TriviaDifficulty.HARD, TriviaMode.CASUAL),

    // Television
    CHANNEL_SURFER(16, R.string.achievement_easy_television, R.string.achievement_easy_television_desc, TriviaCategory.ENTERTAINMENT_TELEVISION, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    PRIME_TIME_PLAYER(17, R.string.achievement_medium_television, R.string.achievement_medium_television_desc, TriviaCategory.ENTERTAINMENT_TELEVISION, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    TV_TITAN(18, R.string.achievement_hard_television, R.string.achievement_hard_television_desc, TriviaCategory.ENTERTAINMENT_TELEVISION, TriviaDifficulty.HARD, TriviaMode.CASUAL),

    // Video Games
    PLAYER_ONE_READY(19, R.string.achievement_easy_video_games, R.string.achievement_easy_video_games_desc, TriviaCategory.ENTERTAINMENT_VIDEO_GAMES, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    ELITE_GAMER(20, R.string.achievement_medium_video_games, R.string.achievement_medium_video_games_desc, TriviaCategory.ENTERTAINMENT_VIDEO_GAMES, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    LEGENDARY_PLAYER(21, R.string.achievement_hard_video_games, R.string.achievement_hard_video_games_desc, TriviaCategory.ENTERTAINMENT_VIDEO_GAMES, TriviaDifficulty.HARD, TriviaMode.CASUAL),

    // Board Games
    CASUAL_GAMER(22, R.string.achievement_easy_board_games, R.string.achievement_easy_board_games_desc, TriviaCategory.ENTERTAINMENT_BOARD_GAMES, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    STRATEGY_SPECIALIST(23, R.string.achievement_medium_board_games, R.string.achievement_medium_board_games_desc, TriviaCategory.ENTERTAINMENT_BOARD_GAMES, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    GRANDMASTER(24, R.string.achievement_hard_board_games, R.string.achievement_hard_board_games_desc, TriviaCategory.ENTERTAINMENT_BOARD_GAMES, TriviaDifficulty.HARD, TriviaMode.CASUAL),

    // Science & Nature
    CURIOUS_EXPLORER(25, R.string.achievement_easy_science_nature, R.string.achievement_easy_science_nature_desc, TriviaCategory.SCIENCE_NATURE, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    NATURAL_PHILOSOPHER(26, R.string.achievement_medium_science_nature, R.string.achievement_medium_science_nature_desc, TriviaCategory.SCIENCE_NATURE, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    SCIENCE_SAGE(27, R.string.achievement_hard_science_nature, R.string.achievement_hard_science_nature_desc, TriviaCategory.SCIENCE_NATURE, TriviaDifficulty.HARD, TriviaMode.CASUAL),

    // Computers
    DEBUG_BEGINNER(28, R.string.achievement_easy_computers, R.string.achievement_easy_computers_desc, TriviaCategory.SCIENCE_COMPUTERS, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    CODE_WARRIOR(29, R.string.achievement_medium_computers, R.string.achievement_medium_computers_desc, TriviaCategory.SCIENCE_COMPUTERS, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    TECH_TITAN(30, R.string.achievement_hard_computers, R.string.achievement_hard_computers_desc, TriviaCategory.SCIENCE_COMPUTERS, TriviaDifficulty.HARD, TriviaMode.CASUAL),

    // Mathematics
    NUMBER_NOVICE(31, R.string.achievement_easy_mathematics, R.string.achievement_easy_mathematics_desc, TriviaCategory.SCIENCE_MATHEMATICS, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    FORMULA_MASTER(32, R.string.achievement_medium_mathematics, R.string.achievement_medium_mathematics_desc, TriviaCategory.SCIENCE_MATHEMATICS, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    MATHEMATICAL_GENIUS(33, R.string.achievement_hard_mathematics, R.string.achievement_hard_mathematics_desc, TriviaCategory.SCIENCE_MATHEMATICS, TriviaDifficulty.HARD, TriviaMode.CASUAL),

    // Mythology
    MYTH_SEEKER(34, R.string.achievement_easy_mythology, R.string.achievement_easy_mythology_desc, TriviaCategory.MYTHOLOGY, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    LEGEND_HUNTER(35, R.string.achievement_medium_mythology, R.string.achievement_medium_mythology_desc, TriviaCategory.MYTHOLOGY, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    MYTHOLOGY_SAGE(36, R.string.achievement_hard_mythology, R.string.achievement_hard_mythology_desc, TriviaCategory.MYTHOLOGY, TriviaDifficulty.HARD, TriviaMode.CASUAL),

    // Sports
    ROOKIE_PLAYER(37, R.string.achievement_easy_sports, R.string.achievement_easy_sports_desc, TriviaCategory.SPORTS, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    ALL_STAR_ATHLETE(38, R.string.achievement_medium_sports, R.string.achievement_medium_sports_desc, TriviaCategory.SPORTS, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    SPORTS_LEGEND(39, R.string.achievement_hard_sports, R.string.achievement_hard_sports_desc, TriviaCategory.SPORTS, TriviaDifficulty.HARD, TriviaMode.CASUAL),

    // Geography
    GLOBE_TROTTER(40, R.string.achievement_easy_geography, R.string.achievement_easy_geography_desc, TriviaCategory.GEOGRAPHY, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    WORLD_EXPLORER(41, R.string.achievement_medium_geography, R.string.achievement_medium_geography_desc, TriviaCategory.GEOGRAPHY, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    ATLAS_MASTER(42, R.string.achievement_hard_geography, R.string.achievement_hard_geography_desc, TriviaCategory.GEOGRAPHY, TriviaDifficulty.HARD, TriviaMode.CASUAL),

    // History
    TIME_TRAVELER_TRAINEE(43, R.string.achievement_easy_history, R.string.achievement_easy_history_desc, TriviaCategory.HISTORY, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    CHRONICLE_KEEPER(44, R.string.achievement_medium_history, R.string.achievement_medium_history_desc, TriviaCategory.HISTORY, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    HISTORY_SCHOLAR(45, R.string.achievement_hard_history, R.string.achievement_hard_history_desc, TriviaCategory.HISTORY, TriviaDifficulty.HARD, TriviaMode.CASUAL),

    // Politics
    CIVIC_STUDENT(46, R.string.achievement_easy_politics, R.string.achievement_easy_politics_desc, TriviaCategory.POLITICS, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    POLICY_PUNDIT(47, R.string.achievement_medium_politics, R.string.achievement_medium_politics_desc, TriviaCategory.POLITICS, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    POLITICAL_SAGE(48, R.string.achievement_hard_politics, R.string.achievement_hard_politics_desc, TriviaCategory.POLITICS, TriviaDifficulty.HARD, TriviaMode.CASUAL),

    // Art
    ASPIRING_ARTIST(49, R.string.achievement_easy_art, R.string.achievement_easy_art_desc, TriviaCategory.ART, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    GALLERY_GUIDE(50, R.string.achievement_medium_art, R.string.achievement_medium_art_desc, TriviaCategory.ART, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    ART_CONNOISSEUR(51, R.string.achievement_hard_art, R.string.achievement_hard_art_desc, TriviaCategory.ART, TriviaDifficulty.HARD, TriviaMode.CASUAL),

    // Celebrities
    FAN_FAVORITE(52, R.string.achievement_easy_celebrities, R.string.achievement_easy_celebrities_desc, TriviaCategory.CELEBRITIES, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    STAR_TRACKER(53, R.string.achievement_medium_celebrities, R.string.achievement_medium_celebrities_desc, TriviaCategory.CELEBRITIES, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    CELEBRITY_EXPERT(54, R.string.achievement_hard_celebrities, R.string.achievement_hard_celebrities_desc, TriviaCategory.CELEBRITIES, TriviaDifficulty.HARD, TriviaMode.CASUAL),

    // Animals
    ANIMAL_FRIEND(55, R.string.achievement_easy_animals, R.string.achievement_easy_animals_desc, TriviaCategory.ANIMALS, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    WILDLIFE_RANGER(56, R.string.achievement_medium_animals, R.string.achievement_medium_animals_desc, TriviaCategory.ANIMALS, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    ZOOLOGY_EXPERT(57, R.string.achievement_hard_animals, R.string.achievement_hard_animals_desc, TriviaCategory.ANIMALS, TriviaDifficulty.HARD, TriviaMode.CASUAL),

    // Vehicles
    STUDENT_DRIVER(58, R.string.achievement_easy_vehicles, R.string.achievement_easy_vehicles_desc, TriviaCategory.VEHICLES, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    GEAR_HEAD(59, R.string.achievement_medium_vehicles, R.string.achievement_medium_vehicles_desc, TriviaCategory.VEHICLES, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    MOTOR_MASTER(60, R.string.achievement_hard_vehicles, R.string.achievement_hard_vehicles_desc, TriviaCategory.VEHICLES, TriviaDifficulty.HARD, TriviaMode.CASUAL),

    // Comics
    COMIC_ROOKIE(61, R.string.achievement_easy_comics, R.string.achievement_easy_comics_desc, TriviaCategory.ENTERTAINMENT_COMICS, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    PANEL_PRO(62, R.string.achievement_medium_comics, R.string.achievement_medium_comics_desc, TriviaCategory.ENTERTAINMENT_COMICS, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    COMICS_LEGEND(63, R.string.achievement_hard_comics, R.string.achievement_hard_comics_desc, TriviaCategory.ENTERTAINMENT_COMICS, TriviaDifficulty.HARD, TriviaMode.CASUAL),

    // Gadgets
    TECH_TRAINEE(64, R.string.achievement_easy_gadgets, R.string.achievement_easy_gadgets_desc, TriviaCategory.SCIENCE_GADGETS, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    DEVICE_GURU(65, R.string.achievement_medium_gadgets, R.string.achievement_medium_gadgets_desc, TriviaCategory.SCIENCE_GADGETS, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    GADGET_GENIUS(66, R.string.achievement_hard_gadgets, R.string.achievement_hard_gadgets_desc, TriviaCategory.SCIENCE_GADGETS, TriviaDifficulty.HARD, TriviaMode.CASUAL),

    // Japanese Anime & Manga
    ANIME_APPRENTICE(67, R.string.achievement_easy_anime_manga, R.string.achievement_easy_anime_manga_desc, TriviaCategory.ENTERTAINMENT_JAPANESE_ANIME_MANGA, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    MANGA_MASTER(68, R.string.achievement_medium_anime_manga, R.string.achievement_medium_anime_manga_desc, TriviaCategory.ENTERTAINMENT_JAPANESE_ANIME_MANGA, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    OTAKU_ORACLE(69, R.string.achievement_hard_anime_manga, R.string.achievement_hard_anime_manga_desc, TriviaCategory.ENTERTAINMENT_JAPANESE_ANIME_MANGA, TriviaDifficulty.HARD, TriviaMode.CASUAL),

    // Cartoon & Animations
    TOON_TRAINEE(70, R.string.achievement_easy_cartoons, R.string.achievement_easy_cartoons_desc, TriviaCategory.ENTERTAINMENT_CARTOON_ANIMATIONS, TriviaDifficulty.EASY, TriviaMode.CASUAL),
    ANIMATION_ACE(71, R.string.achievement_medium_cartoons, R.string.achievement_medium_cartoons_desc, TriviaCategory.ENTERTAINMENT_CARTOON_ANIMATIONS, TriviaDifficulty.NORMAL, TriviaMode.CASUAL),
    CARTOON_CHAMPION(72, R.string.achievement_hard_cartoons, R.string.achievement_hard_cartoons_desc, TriviaCategory.ENTERTAINMENT_CARTOON_ANIMATIONS, TriviaDifficulty.HARD, TriviaMode.CASUAL);


    companion object {
        fun getByArgs(
            id: Int? = null,
            category: TriviaCategory? = null,
            difficulty: TriviaDifficulty? = null,
            mode: TriviaMode? = null
        ): List<TriviaAchievement> {
            return entries.filter { achievement ->
                (id == null || achievement.id == id) &&
                (category == null || achievement.category == category) &&
                (difficulty == null || achievement.difficulty == difficulty) &&
                (mode == null || achievement.mode == mode)
            }
        }
    }
}