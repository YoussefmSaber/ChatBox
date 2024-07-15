package com.pixe.chatapp.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material3.TextButton
import androidx.wear.compose.material3.ripple
import com.pixe.chatapp.R
import com.pixe.chatapp.ui.theme.Colors
import com.pixe.chatapp.ui.theme.Typography
import com.pixe.chatapp.widgets.GoogleButton
import com.pixe.chatapp.widgets.OrSplitter

@Composable
fun OnboardingScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .background(Colors.Onboarding)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ellipse),
            contentDescription = "Ellipse fade"
        )
        Column(

            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_uihut),
                contentDescription = "Logo"
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Connect friends easily & quickly",
                color = Colors.White,
                style = Typography.displayLarge
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Our chat app is the perfect way to stay connected with friends and family.",
                color = Colors.BlueishWhite,
                style = Typography.bodyLarge
            )

            GoogleButton(false)
            OrSplitter()
            Button(
                onClick = {
                    navController.navigate("signup_screen")
                },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonColors(
                    containerColor = Colors.White,
                    contentColor = Colors.DarkGrey,
                    disabledContentColor = Colors.White,
                    disabledContainerColor = Colors.OtherGrey
                ),
                modifier = Modifier
                    .fillMaxWidth(0.9f),
            ) {
                Text(
                    text = "Sign up with email",
                    Modifier.padding(10.dp),
                    style = Typography.titleSmall,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Existing account?",
                    style = Typography.labelMedium,
                    color = Colors.White
                )
                TextButton(onClick = {
                    navController.navigate("login_screen")
                    ripple(
                        false,
                        color = Color.Transparent,
                        radius = 0.dp
                    )
                }) {
                    Text(
                        text = " Log in",
                        style = Typography.labelLarge,
                        color = Colors.BlueishWhite
                    )
                }
            }
        }

    }

}