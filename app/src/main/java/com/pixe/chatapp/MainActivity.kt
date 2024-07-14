package com.pixe.chatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pixe.chatapp.navigation.Navigation
import com.pixe.chatapp.screens.ChatScreen
import com.pixe.chatapp.ui.theme.ChatAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatAppTheme {
                Navigation()
            }
        }
    }
}