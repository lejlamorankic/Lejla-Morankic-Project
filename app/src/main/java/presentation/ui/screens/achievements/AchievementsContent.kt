package presentation.ui.screens.achievements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.ui.components.ScreenTopBar
import presentation.ui.screens.achievements.components.AchievementItem
import presentation.viewmodel.AchievementsUiState

@Composable
fun AchievementsContent(
    uiState: AchievementsUiState,
    onBackClick: () -> Unit
) {
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
            Text("No items available")
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