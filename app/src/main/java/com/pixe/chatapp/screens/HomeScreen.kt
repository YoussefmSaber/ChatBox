package com.pixe.chatapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pixe.chatapp.data.entity.BottomNav
import com.pixe.chatapp.data.entity.BottomNavItems
import com.pixe.chatapp.ui.theme.Colors
import com.pixe.chatapp.widgets.BottomNavigationBar
import compose.icons.FeatherIcons
import compose.icons.feathericons.Star

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
