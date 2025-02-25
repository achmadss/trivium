package dev.achmad.trivium.ui.screens.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.achmad.core.model.category.TriviaCategory
import dev.achmad.trivium.ui.components.TriviumTopBar
import dev.achmad.trivium.ui.components.category.TriviumCategoryListItem
import dev.achmad.trivium.ui.components.category.TriviumCategoryListItemState
import dev.achmad.trivium.ui.screens.new_game.NewGameScreenViewModel
import dev.achmad.trivium.ui.screens.new_game.NewGameState
import dev.achmad.trivium.ui.theme.background100
import dev.achmad.trivium.ui.theme.triviumSecondary
import dev.achmad.trivium.ui.utils.activityViewModel
import kotlinx.serialization.Serializable

@Serializable
object CategoryRoute

fun NavGraphBuilder.category(
    onBack: () -> Unit,
) {
    composable<CategoryRoute> {
        val viewModel: NewGameScreenViewModel = activityViewModel()
        val state by viewModel.state.collectAsState()
        CategoryScreen(
            state = state,
            onBack = onBack,
            onSelectCategory = { category ->
                viewModel.selectCategory(category)
            }
        )
    }
}

@Composable
fun CategoryScreen(
    state: NewGameState = NewGameState(),
    onBack: () -> Unit = {},
    onSelectCategory: (TriviaCategory) -> Unit = {}
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = background100,
        topBar = {
            TriviumTopBar(
                text = "Category",
                onBack = onBack,
                actions = {
                    IconButton(
                        onClick = onBack,
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = null,
                            tint = triviumSecondary
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
            ,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(TriviaCategory.entries) { category ->
                TriviumCategoryListItem(
                    title = category.displayName,
                    icon = ImageVector.vectorResource(category.icon),
                    state = when {
                        state.category != category -> TriviumCategoryListItemState.INACTIVE
                        else -> TriviumCategoryListItemState.ACTIVE
                    },
                    onClick = {
                        onSelectCategory(category)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCategoryScreen() {
    CategoryScreen()
}