package com.pixe.chatapp.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import com.pixe.chatapp.ui.theme.Colors
import com.pixe.chatapp.ui.theme.Typography

@Composable
fun TextWithLineBehind(text: String, color: Color) {
    Box {
        Text(
            text = text,
            style = Typography.titleMedium,
            modifier = Modifier
                .align(Alignment.Center)
                .layout { measurable, constraints ->
                    // Measure the text
                    val placeable = measurable.measure(constraints)

                    // Layout the text
                    layout(
                        placeable.width,
                        placeable.height + 5
                    ) { // Add 5dp for the box height
                        placeable.placeRelative(0, 0) // Place the text at the top
                    }
                }
                .drawBehind {
                    // Draw the box after the text is drawn
                    drawRect(
                        color = color,
                        topLeft = Offset(
                            0f,
                            size.height - 8.dp.toPx()
                        ), // Position at the bottom
                        size = Size(
                            size.width,
                            8.dp.toPx()
                        ) // Width of text, height of 5dp
                    )
                }
        )
    }
}