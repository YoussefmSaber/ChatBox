package com.pixe.chatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.pixe.chatapp.navigation.Navigation
import com.pixe.chatapp.ui.theme.ChatAppTheme
import com.pixe.chatapp.widgets.UserChatItem


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatAppTheme {
                UserChatItem()
            }
        }
    }
}