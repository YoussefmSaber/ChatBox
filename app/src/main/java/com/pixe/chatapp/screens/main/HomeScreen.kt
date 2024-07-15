package com.pixe.chatapp.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pixe.chatapp.data.entity.BottomNav
import com.pixe.chatapp.data.entity.BottomNavItems
import com.pixe.chatapp.widgets.BottomNavigationBar

@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController, BottomNavItems)
        },
    ) { it ->
        Box(modifier = Modifier.padding(it)) {}
        NavHost(navController = navController, startDestination = BottomNav.Message.route) {
            composable(BottomNav.Message.route) {
                MessageScreen()
            }
            composable(BottomNav.Contacts.route) {
                ContactsScreen()
            }
            composable(BottomNav.Settings.route) {
                SettingsScreen()
            }
        }
    }
}
