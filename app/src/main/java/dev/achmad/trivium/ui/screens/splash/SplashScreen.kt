package dev.achmad.trivium.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.achmad.trivium.R
import dev.achmad.trivium.ui.theme.splashBackground
import dev.achmad.trivium.ui.utils.rememberResourceBitmapPainter
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

@Serializable
object SplashRoute

fun NavGraphBuilder.splash(
    onFinish: () -> Unit = {},
) {
    composable<SplashRoute> {
        SplashScreen(onFinish)
    }
}

@Composable
private fun SplashScreen(
    onFinish: () -> Unit = {},
) {
    LaunchedEffect(Unit) {
        delay(1000)
        onFinish()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(splashBackground)
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .size(256.dp),
            painter = rememberResourceBitmapPainter(id = R.drawable.ic_launcher_foreground), // TODO change logo
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SplashScreen()
}