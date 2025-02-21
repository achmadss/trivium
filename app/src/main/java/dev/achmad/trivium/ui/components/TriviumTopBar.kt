package dev.achmad.trivium.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.NavigateBefore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.achmad.trivium.ui.theme.background100
import dev.achmad.trivium.ui.theme.primaryDark
import dev.achmad.trivium.ui.theme.secondary
import dev.achmad.trivium.ui.utils.bottomBorder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TriviumTopBar(
    text: String,
    onBack: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        modifier = Modifier
            .bottomBorder(1.dp, primaryDark),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = background100,
            navigationIconContentColor = primaryDark,
            titleContentColor = secondary,
        ),
        title = {
            Text(
                text = text,
                color = secondary,
                style = MaterialTheme.typography.titleMedium,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBack
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.NavigateBefore,
                    contentDescription = null,
                )
            }
        },
        actions = actions
    )
}

@Preview
@Composable
private fun PreviewTriviumTopBar() {
    TriviumTopBar(
        text = "Achievement",
        onBack = {},
    )
}