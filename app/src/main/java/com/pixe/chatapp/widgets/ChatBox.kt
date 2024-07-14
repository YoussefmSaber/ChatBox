package com.pixe.chatapp.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.pixe.chatapp.data.entity.Message
import com.pixe.chatapp.ui.theme.Colors
import com.pixe.chatapp.utils.receiverTestID
import java.time.LocalDateTime
import java.util.UUID

@Composable
fun ChatBox(modifier: Modifier, onSendMessage: (Message) -> Unit, sender: String) {
    var chatBoxValue by remember { mutableStateOf(TextFieldValue("")) }
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            modifier = Modifier
                .weight(1f),
            shape = RoundedCornerShape(24.dp),
            value = chatBoxValue,
            onValueChange = { newText ->
                chatBoxValue = newText
            },
            placeholder = {
                Text(text = "Type something")
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = {
                val message = Message(
                    id = UUID.randomUUID().toString(),
                    createdAt = LocalDateTime.now().toString(),
                    text = chatBoxValue.text,
                    image = "",
                    sender = sender,
                    receiver = receiverTestID
                )
                onSendMessage(message)
                chatBoxValue = TextFieldValue("")
            },
            modifier = Modifier
                .background(color = Colors.Main, shape = CircleShape),
            content = {
                Icon(
                    Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Send Button",
                    tint = Colors.BlueishWhite,
                )
            }
        )
    }
}