package presentation.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import presentation.ui.components.ScreenTopBar
import presentation.ui.screens.details.components.DetailCard
import presentation.viewmodel.GoalDetailViewModel

@Composable
fun GoalDetailScreen(
    goalName: String,
    goalStatus: String,
    onBackClick: () -> Unit
) {
    val viewModel: GoalDetailViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(goalName, goalStatus) {
        viewModel.setGoalDetails(goalName, goalStatus)
    }

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