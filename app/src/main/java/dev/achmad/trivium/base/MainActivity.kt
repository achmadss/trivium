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
import dev.achmad.trivium.ui.screens.splash.SplashRoute
import dev.achmad.trivium.ui.screens.splash.splash
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
                    startDestination = SplashRoute,
                    enterTransition = { materialSharedAxisXIn(true, slideDistance) },
                    exitTransition = { materialSharedAxisXOut(true, slideDistance) },
                    popEnterTransition = { materialSharedAxisXIn(false, slideDistance) },
                    popExitTransition = { materialSharedAxisXOut(false, slideDistance) }
                ) {
                    splash(
                        onFinish = {
                            mainViewModel.onNewIntent(intent)
                        }
                    )
                }

                LaunchedEffect(newIntent) {
                    val intent = newIntent ?: return@LaunchedEffect
                    when(intent.getStringExtra(TRIVIUM_ACTION)) {
                        // handle intents here
                        else -> Unit
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