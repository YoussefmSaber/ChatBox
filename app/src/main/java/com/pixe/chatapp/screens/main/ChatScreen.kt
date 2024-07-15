package com.pixe.chatapp.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pixe.chatapp.data.network.SupabaseClient.supabase
import com.pixe.chatapp.utils.receiverTestID
import com.pixe.chatapp.viewModel.ChatViewModel
import com.pixe.chatapp.widgets.ChatBox
import com.pixe.chatapp.widgets.ChatBubbles
import io.github.jan.supabase.gotrue.auth

@Preview(showBackground = true)
@Composable
fun ChatScreen() {

    val viewModel: ChatViewModel = viewModel()
    val messages by viewModel.userMessageLive.collectAsState(emptyList())

    LaunchedEffect(Unit) {
        supabase.auth.currentUserOrNull()
            ?.let { viewModel.getUserChatMessages(it.id, otherPersonId = receiverTestID) }
        viewModel.realtimeDb(this)
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {
        val (messageList, chatBox) = createRefs()
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(messageList) {
                    top.linkTo(parent.top)
                    bottom.linkTo(chatBox.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                }
        ) {
            items(messages) { message ->
                ChatBubbles(message, supabase.auth.currentUserOrNull()?.id!!)
            }
        }
        ChatBox(
            modifier = Modifier
                .constrainAs(chatBox) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onSendMessage = { newMessage ->
                viewModel.sendMessage(newMessage)
            }, // Assuming ChatBox has a callback for sending messages
            sender = supabase.auth.currentUserOrNull()?.id!!
        )
    }
}