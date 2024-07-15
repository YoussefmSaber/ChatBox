package com.pixe.chatapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pixe.chatapp.screens.main.ChatScreen
import com.pixe.chatapp.screens.main.HomeScreen
import com.pixe.chatapp.screens.auth.LoginScreen
import com.pixe.chatapp.screens.auth.OnboardingScreen
import com.pixe.chatapp.screens.auth.SignupScreen
import com.pixe.chatapp.viewModel.AuthenticationViewModel

@Composable
fun Navigation() {

    val navController = rememberNavController()

    val viewModel: AuthenticationViewModel = viewModel()

    var defaultStart = "home_screen"

    LaunchedEffect(Unit) {
        if (viewModel.getUserAccessToken()?.accessToken?.isEmpty() == false)
            defaultStart = "chat_screen"
    }

    NavHost(navController = navController, startDestination = defaultStart,
        builder = {
            composable("onboarding_screen", content = {
                OnboardingScreen(navController = navController)
            })
            composable("signup_screen", content = {
                SignupScreen(navController = navController)
            })
            composable("login_screen", content = {
                LoginScreen(navController = navController)
            })
            composable("chat_screen", content = {
                ChatScreen()
            })
            composable("home_screen", content = {
                HomeScreen()
            })
        })
}