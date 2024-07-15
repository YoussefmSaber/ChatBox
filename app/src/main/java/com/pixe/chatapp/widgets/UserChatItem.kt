package com.pixe.chatapp.widgets

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.pixe.chatapp.ui.theme.Colors
import com.pixe.chatapp.ui.theme.Typography

@OptIn(ExperimentalGlideComposeApi::class)
@Preview(showBackground = true)
@Composable
fun UserChatItem() {
    ConstraintLayout(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        val (userImage, userInfo, notification) = createRefs()

        // User image
        Surface(
            shape = CircleShape,
            modifier = Modifier
                .size(50.dp)
                .constrainAs(userImage) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        ) {
            GlideImage(
                model = "https://wallpapers-clan.com/wp-content/uploads/2023/05/cool-pfp-02.jpg",
                contentDescription = "User Image",
                modifier = Modifier
                    .background(Colors.Black)
            )

        }

        // Right most side content
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(userInfo) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(userImage.end)
                }
        ) {
            // Sender name
            Text(text = "Karim Hussein", style = Typography.labelLarge)
            Spacer(modifier = Modifier.height(4.dp))
            // Last message
            Text(text = "Hello, How are you?", style = Typography.labelSmall, color = Colors.Grey)
        }

        // Left most side content
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.constrainAs(notification) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        ) {
            // Time last message received
            Text(text = "2 min ago", style = Typography.labelSmall, color = Colors.Grey)
            Spacer(modifier = Modifier.height(4.dp))

            // Number of message
            Badge(
                containerColor = Colors.Error,
                contentColor = Colors.White,
                content = {
                    Text(text = "2", style = Typography.labelSmall)
                }
            )
        }
    }
}