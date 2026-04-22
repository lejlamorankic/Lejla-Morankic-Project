package presentation.ui.screens.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CategoryChip(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    AssistChip(
        onClick = onClick,
        label = {
            Text(
                text = title,
                color = if (isSelected) Color.White else Color.Black
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = if (isSelected) Color(0xFFE91E63) else Color.LightGray,
            labelColor = if (isSelected) Color.White else Color.Black
        ),
        modifier = Modifier.padding(end = 8.dp)
    )
}