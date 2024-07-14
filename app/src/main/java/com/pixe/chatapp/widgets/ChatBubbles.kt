package com.pixe.chatapp.widgets

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.core.widgets.Optimizer
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.pixe.chatapp.data.entity.Message
import com.pixe.chatapp.ui.theme.*

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ChatBubbles(message: Message, myId: String) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        Optimizer.OPTIMIZATION_STANDARD
    ) {
        val box = createRef()
        Box(
            modifier = Modifier
                .constrainAs(box) {
                    Log.d("Chat Bubbles Debug", "ChatBubbles: ${message.text} || ${message.sender}")
                    if (message.sender == myId)
                        end.linkTo(parent.end)
                    else
                        start.linkTo(parent.start)
                }
                .clip(
                    RoundedCornerShape(
                        topStart = 24f,
                        topEnd = 24f,
                        bottomStart = if (message.sender == myId) 24f else 0f,
                        bottomEnd = if (message.sender == myId) 0f else 24f
                    )
                )
                .background(if (message.sender == myId) Colors.Main else Colors.BlueishWhite)
                .padding(8.dp)
                .requiredWidthIn(min = 50.dp, max = 200.dp)
        ) {
            Column() {
                if (message.text.isNullOrEmpty()) {
                    GlideImage(
                        model = message.image!!,
                        contentDescription = "Chat Image",
                    )
                } else {
                    Text(
                        text = message.text,
                        fontSize = 12.sp,
                        color = if (message.sender == myId) Colors.BlueishWhite else Colors.DarkGrey
                    )
                }

            }
        }
    }

}
