package com.pixe.chatapp.widgets

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pixe.chatapp.data.entity.BottomNav
import com.pixe.chatapp.ui.theme.Colors

@Composable
fun BottomNavigationBar(navController: NavHostController, bottomNavItems: List<BottomNav>) {
    var selectedItem by remember { mutableIntStateOf(0) }

    NavigationBar(
        containerColor = Colors.DarkWhite,
        tonalElevation = 20.dp
    ) {
        bottomNavItems.forEachIndexed { index, screen ->
            NavigationBarItem(
                onClick = {
                    when (screen.route) {
                        "message" -> {
                            navController.navigate(BottomNav.Message.route)
                            selectedItem = 0
                        }

                        "Contacts" -> {
                            navController.navigate(BottomNav.Contacts.route)
                            selectedItem = 1
                        }

                        "Settings" -> {
                            navController.navigate(BottomNav.Settings.route)
                            selectedItem = 2
                        }
                    }
                },
                label = {
                    Text(text = screen.label)
                },
                selected = index == selectedItem,
                icon = {
                    Icon(
                        imageVector = screen.icon, contentDescription = screen.label
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Colors.Main,
                    unselectedIconColor = Colors.Grey,
                    selectedTextColor = Colors.Main,
                    unselectedTextColor = Colors.Grey,
                    indicatorColor = Colors.MainTransparent
                )
            )
        }
    }
}
