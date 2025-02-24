package dev.achmad.trivium.base

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import dagger.hilt.android.AndroidEntryPoint
import dev.achmad.core.TRIVIUM_ACTION
import dev.achmad.trivium.ui.screens.achievement.AchievementRoute
import dev.achmad.trivium.ui.screens.achievement.achievement
import dev.achmad.trivium.ui.screens.category.CategoryRoute
import dev.achmad.trivium.ui.screens.category.category
import dev.achmad.trivium.ui.screens.game.GameRoute
import dev.achmad.trivium.ui.screens.game.game
import dev.achmad.trivium.ui.screens.main_menu.MainMenuRoute
import dev.achmad.trivium.ui.screens.main_menu.mainMenu
import dev.achmad.trivium.ui.screens.new_game.NewGameRoute
import dev.achmad.trivium.ui.screens.new_game.newGame
import dev.achmad.trivium.ui.theme.LocalNavController
import dev.achmad.trivium.ui.theme.TriviumTheme
import soup.compose.material.motion.animation.materialSharedAxisXIn
import soup.compose.material.motion.animation.materialSharedAxisXOut
import soup.compose.material.motion.animation.rememberSlideDistance

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TriviumTheme {
                val slideDistance = rememberSlideDistance()
                val navController = LocalNavController.current
                val newIntent by mainViewModel.newIntent.collectAsState()

                NavHost(
                    navController = navController,
                    startDestination = MainMenuRoute,
                    enterTransition = { materialSharedAxisXIn(true, slideDistance) },
                    exitTransition = { materialSharedAxisXOut(true, slideDistance) },
                    popEnterTransition = { materialSharedAxisXIn(false, slideDistance) },
                    popExitTransition = { materialSharedAxisXOut(false, slideDistance) }
                ) {
                    // TODO google how to handle intent from splash
                    mainMenu(
                        onClickPlay = {
                            navController.navigate(NewGameRoute)
                        },
                        onClickAchievements = {
                            navController.navigate(AchievementRoute)
                        }
                    )
                    achievement(
                        onBack = {
                            navController.navigateUp()
                        }
                    )
                    category(
                        onBack = {
                            navController.navigateUp()
                        }
                    )
                    newGame(
                        onBack = {
                            navController.navigateUp()
                        },
                        onNavigateToCategory = {
                            navController.navigate(CategoryRoute)
                        },
                        onNavigateToGame = { mode, difficulty, category, type ->
                            navController.navigate(
                                GameRoute(
                                    mode = mode,
                                    difficulty = difficulty,
                                    category = category,
                                    type = type
                                )
                            )
                        }
                    )
                    game(
                        onQuit = {
                            navController.navigateUp()
                        },
                        onFinish = {
                            // TODO navigate to end screen with data from viewmodel
                        }
                    )
                }

                LaunchedEffect(newIntent) {
                    val intent = newIntent ?: return@LaunchedEffect
                    when(intent.getStringExtra(TRIVIUM_ACTION)) {
                        // handle intents here
                        else -> {
                            navController.navigate(MainMenuRoute)
                        }
                    }
                    mainViewModel.resetIntent()
                }

            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        mainViewModel.onNewIntent(intent)
    }

}