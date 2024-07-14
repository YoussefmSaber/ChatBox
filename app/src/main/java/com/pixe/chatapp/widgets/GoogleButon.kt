package com.pixe.chatapp.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.ripple
import com.pixe.chatapp.R
import com.pixe.chatapp.ui.theme.Colors
import com.pixe.chatapp.ui.theme.Typography
import com.pixe.chatapp.ui.theme.cardColors

@Composable
fun GoogleButton(isColored: Boolean) = OutlinedButton(
    colors = cardColors(),
    shape = CircleShape,
    border = BorderStroke(1.dp, color = Colors.Grey),
    modifier = Modifier
        .padding(16.dp)
        .clickable {
            ripple(
                radius = 30.dp,
                color = Colors.Main,
                bounded = true
            )
        },
    onClick = {

    }
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 4.dp),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_google_drawable),
            contentDescription = "Google Icon",
            tint = if (isColored) Color.Unspecified else Colors.Grey
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Sign in using Google",
            style = Typography.bodyMedium,
            color = if (isColored) Color.DarkGray else Colors.White
        )
    }
}