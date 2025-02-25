package dev.achmad.trivium.ui.components.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import dev.achmad.trivium.ui.theme.triviumPrimary
import dev.achmad.trivium.ui.theme.triviumPrimaryDark
import dev.achmad.trivium.ui.theme.triviumSecondary

enum class TriviumCategoryListItemState {
    ACTIVE, INACTIVE
}

private data class TriviumAchievementListItemColors(
    val backgroundColor: Color = background80,
    val titleTextColor: Color = triviumSecondary,
    val iconColor: Color = triviumPrimaryDark,
    val iconBackgroundColor: Color = background40,
)

@Composable
fun TriviumCategoryListItem(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector,
    state: TriviumCategoryListItemState = TriviumCategoryListItemState.INACTIVE,
    onClick: () -> Unit = {},
) {
    val colors = when(state) {
        TriviumCategoryListItemState.ACTIVE -> {
            TriviumAchievementListItemColors(
                backgroundColor = triviumPrimaryDark,
                titleTextColor = triviumSecondary,
                iconColor = triviumSecondary,
                iconBackgroundColor = triviumPrimary,
            )
        }
        TriviumCategoryListItemState.INACTIVE -> {
            TriviumAchievementListItemColors(
                backgroundColor = background80,
                titleTextColor = triviumDisabledText,
                iconColor = triviumPrimaryDark,
                iconBackgroundColor = background40,
            )
        }
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(colors.backgroundColor)
            .clickable { onClick() }
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(36.dp)
                    .background(colors.iconBackgroundColor),
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(18.dp)
                        .align(Alignment.Center),
                    tint = colors.iconColor,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = title,
                    color = colors.titleTextColor,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }

}

@Preview
@Composable
private fun PreviewTriviumCategoryListItem() {
    TriviumCategoryListItem(
        title = "Achievement",
        icon = ImageVector.vectorResource(R.drawable.trophy),
        state = TriviumCategoryListItemState.ACTIVE,
    )
}

@Preview
@Composable
private fun PreviewTriviumCategoryListItemInactive() {
    TriviumCategoryListItem(
        title = "Achievement",
        icon = ImageVector.vectorResource(R.drawable.trophy),
    )
}