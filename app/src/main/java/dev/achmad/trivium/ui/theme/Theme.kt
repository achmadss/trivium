package dev.achmad.trivium.ui.theme

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.achmad.trivium.base.AppTheme
import dev.achmad.trivium.base.MainViewModel

val LocalNavController = compositionLocalOf<NavHostController> {
    error("No NavController provided")
}
val LocalTypography = compositionLocalOf { Typography }

@Composable
fun TriviumTheme(
    content: @Composable () -> Unit
) {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<MainViewModel>()
    val appTheme by viewModel.appTheme.collectAsState()

    val darkTheme = when (appTheme) {
        AppTheme.SYSTEM -> isSystemInDarkTheme()
        AppTheme.DARK -> true
        AppTheme.LIGHT -> false
    }

    val typography = Typography

    SystemBarColor(null, darkTheme)

    CompositionLocalProvider(
        LocalNavController provides navController,
        LocalTypography provides typography,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(background60),
        ) {
            content()
        }
    }
}

@Suppress("DEPRECATION")
@Composable
fun SystemBarColor(color: Color?, darkTheme: Boolean = false) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            color?.let {
                val argb = it.toArgb()
                window.navigationBarColor = argb
                window.statusBarColor = argb
            }
            val insetsController = window.decorView.let(ViewCompat::getWindowInsetsController)
            insetsController?.let {
                it.apply {
                    isAppearanceLightStatusBars = !darkTheme
                    isAppearanceLightNavigationBars = !darkTheme
                }
            }
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }
}

@Suppress("DEPRECATION")
@Composable
fun NavigationBarColor(color: Color) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.navigationBarColor = color.toArgb()
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }
}

@Suppress("DEPRECATION")
@Composable
fun StatusBarColor(color: Color) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = color.toArgb()
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }
}