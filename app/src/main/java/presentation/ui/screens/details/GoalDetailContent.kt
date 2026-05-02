package presentation.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.ui.components.ScreenTopBar
import presentation.ui.screens.details.components.DetailCard
import presentation.viewmodel.GoalDetailUiState

@Composable
fun GoalDetailContent(
    uiState: GoalDetailUiState,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ScreenTopBar(
            title = "Goal Details",
            onBackClick = onBackClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        DetailCard(
            title = "Goal Name",
            value = uiState.goalName
        )

        Spacer(modifier = Modifier.height(8.dp))

        DetailCard(
            title = "Status",
            value = uiState.goalStatus
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}