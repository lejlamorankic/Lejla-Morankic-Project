package presentation.ui.screens.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CategoryChip(
    title: String
) {
    AssistChip(
        onClick = { },
        label = {
            Text(text = title)
        },
        modifier = Modifier.padding(end = 8.dp)
    )
}