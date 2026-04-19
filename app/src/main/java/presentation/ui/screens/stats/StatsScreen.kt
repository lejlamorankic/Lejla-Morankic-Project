package presentation.ui.screens.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.ui.components.ScreenTopBar
import presentation.ui.screens.stats.components.StatBadge
import presentation.ui.screens.stats.components.StatRow

@Composable
fun StatsScreen(
    onBackClick: () -> Unit
) {
    val badges = listOf(
        "Active",
        "Focused",
        "Consistent",
        "Motivated"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ScreenTopBar(
            title = "Quick Stats",
            onBackClick = onBackClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow {
            items(badges) { badge ->
                StatBadge(title = badge)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        StatRow(label = "Total Goals", value = "5")
        StatRow(label = "Current XP", value = "100")
        StatRow(label = "Achievements", value = "4")

        Spacer(modifier = Modifier.height(16.dp))
    }
}