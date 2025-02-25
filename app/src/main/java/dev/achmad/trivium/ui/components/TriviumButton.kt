package dev.achmad.trivium.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.achmad.trivium.ui.theme.background80
import dev.achmad.trivium.ui.theme.triviumDisabled
import dev.achmad.trivium.ui.theme.triviumDisabledText
import dev.achmad.trivium.ui.theme.triviumPrimaryDark
import dev.achmad.trivium.ui.theme.triviumSecondary

enum class TriviumFilledButtonState {
    ACTIVE, INACTIVE
}

@Composable
fun TriviumFilledButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    icon: ImageVector? = null,
    border: BorderStroke? = null,
    contentPadding: PaddingValues = PaddingValues(8.dp),
    state: TriviumFilledButtonState = TriviumFilledButtonState.ACTIVE,
    onClick: () -> Unit = {},
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = when(state) {
            TriviumFilledButtonState.ACTIVE -> triviumPrimaryDark
            TriviumFilledButtonState.INACTIVE -> background80
        },
        border = border,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .clickable { onClick() }
                .padding(contentPadding)
        ) {
            Row(
                modifier = Modifier.align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                icon?.let {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = when(state) {
                            TriviumFilledButtonState.ACTIVE -> triviumSecondary
                            TriviumFilledButtonState.INACTIVE -> triviumDisabled
                        }
                    )
                    Spacer(Modifier.width(8.dp))
                }
                text?.let {
                    Text(
                        text = text,
                        color = when(state) {
                            TriviumFilledButtonState.ACTIVE -> triviumSecondary
                            TriviumFilledButtonState.INACTIVE -> triviumDisabledText
                        },
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun PreviewTriviumFilledButton() {
    TriviumFilledButton(
        modifier = Modifier.width(160.dp),
        text = "Play",
        icon = Icons.Outlined.PlayArrow
    ) { }
}

@Preview(showBackground = false)
@Composable
private fun PreviewTriviumFilledButton2() {
    TriviumFilledButton(
        modifier = Modifier.fillMaxWidth(),
        text = "Start Game",
        icon = Icons.Outlined.PlayArrow,
        contentPadding = PaddingValues(16.dp)
    ) { }
}

@Preview(showBackground = false)
@Composable
private fun PreviewTriviumFilledButtonInactive() {
    TriviumFilledButton(
        modifier = Modifier.width(160.dp),
        state = TriviumFilledButtonState.INACTIVE,
        text = "Play",
        icon = Icons.Outlined.PlayArrow
    ) { }
}

@Preview(showBackground = false)
@Composable
private fun PreviewTriviumFilledButtonInactive2() {
    TriviumFilledButton(
        modifier = Modifier.fillMaxWidth(),
        state = TriviumFilledButtonState.INACTIVE,
        text = "Start Game",
        icon = Icons.Outlined.PlayArrow,
        contentPadding = PaddingValues(16.dp)
    ) { }
}