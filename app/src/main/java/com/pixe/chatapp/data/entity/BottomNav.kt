package com.pixe.chatapp.data.entity

import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.EvaIcons
import compose.icons.FeatherIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.MessageCircle
import compose.icons.evaicons.outline.Person
import compose.icons.evaicons.outline.Settings
import compose.icons.feathericons.MessageCircle

sealed class BottomNav(val route: String, val label: String, val icon: ImageVector) {

    data object Message :
        BottomNav(route = "message", label = "Message", icon = EvaIcons.Outline.MessageCircle)

    data object Contacts :
        BottomNav(route = "Contacts", label = "Contacts", icon = EvaIcons.Outline.Person)

    data object Settings :
        BottomNav(route = "Settings", label = "Settings", icon = EvaIcons.Outline.Settings)
}

val BottomNavItems = listOf(
    BottomNav.Message,
    BottomNav.Contacts,
    BottomNav.Settings
)