package presentation.ui.screens.achievements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import presentation.ui.components.ScreenTopBar
import presentation.ui.screens.achievements.components.AchievementItem
import presentation.viewmodel.AchievementsViewModel

@Composable
fun AchievementsScreen(
    onBackClick: () -> Unit
) {
    val viewModel: AchievementsViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ScreenTopBar(
            title = "Achievements",
            onBackClick = onBackClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (uiState.achievements.isEmpty()) {
            androidx.compose.material3.Text("No items available")
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.achievements) { achievement ->
                    AchievementItem(achievement = achievement)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}