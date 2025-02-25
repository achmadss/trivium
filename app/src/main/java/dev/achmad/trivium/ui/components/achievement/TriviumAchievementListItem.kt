package dev.achmad.trivium.ui.components.achievement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.achmad.trivium.R
import dev.achmad.trivium.ui.theme.background40
import dev.achmad.trivium.ui.theme.background80
import dev.achmad.trivium.ui.theme.triviumDisabledText
import dev.achmad.trivium.ui.theme.triviumPrimaryDark
import dev.achmad.trivium.ui.theme.triviumSecondary
import dev.achmad.trivium.ui.utils.transparency

enum class TriviumAchievementListItemState {
    ACTIVE, INACTIVE
}

private data class TriviumAchievementListItemColors(
    val backgroundColor: Color = background80,
    val titleTextColor: Color = triviumSecondary,
    val subtitleTextColor: Color = triviumDisabledText,
    val iconColor: Color = triviumPrimaryDark,
    val iconBackgroundColor: Color = background40,
)

private val LocalColor = compositionLocalOf { TriviumAchievementListItemColors() }

@Composable
fun TriviumAchievementListItem(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    icon: ImageVector = ImageVector.vectorResource(R.drawable.trophy),
    state: TriviumAchievementListItemState = TriviumAchievementListItemState.INACTIVE,
) {
    val colors = when(state) {
        TriviumAchievementListItemState.ACTIVE -> {
            TriviumAchievementListItemColors(
                backgroundColor = background80,
                titleTextColor = triviumSecondary,
                subtitleTextColor = triviumDisabledText,
                iconColor = triviumPrimaryDark,
                iconBackgroundColor = background40,
            )
        }
        TriviumAchievementListItemState.INACTIVE -> {
            TriviumAchievementListItemColors(
                backgroundColor = background80.transparency(),
                titleTextColor = triviumSecondary.transparency(),
                subtitleTextColor = triviumDisabledText.transparency(),
                iconColor = triviumPrimaryDark.transparency(),
                iconBackgroundColor = background40.transparency(),
            )
        }
    }
    CompositionLocalProvider(
        LocalColor provides colors
    ) {
        val color = LocalColor.current
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .background(color.backgroundColor)
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(36.dp)
                        .background(color.iconBackgroundColor),
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier
                            .size(18.dp)
                            .align(Alignment.Center),
                        tint = color.iconColor,
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = title,
                        color = color.titleTextColor,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = subtitle,
                        color = color.subtitleTextColor,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }

}

@Preview
@Composable
private fun PreviewTriviumAchievementListItem() {
    TriviumAchievementListItem(
        title = "Achievement",
        subtitle = "subtitle",
        icon = ImageVector.vectorResource(R.drawable.trophy),
        state = TriviumAchievementListItemState.ACTIVE,
    )
}

@Preview
@Composable
private fun PreviewTriviumAchievementListItemInactive() {
    TriviumAchievementListItem(
        title = "Achievement",
        subtitle = "subtitle",
        icon = ImageVector.vectorResource(R.drawable.trophy),
    )
}