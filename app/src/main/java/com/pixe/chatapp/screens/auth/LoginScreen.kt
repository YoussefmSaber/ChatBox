package com.pixe.chatapp.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.pixe.chatapp.ui.theme.Colors
import com.pixe.chatapp.ui.theme.Typography
import com.pixe.chatapp.ui.theme.textFieldStyles
import com.pixe.chatapp.viewModel.AuthenticationViewModel
import com.pixe.chatapp.widgets.GoogleButton
import com.pixe.chatapp.widgets.OrSplitter
import com.pixe.chatapp.widgets.TextWithLineBehind
import io.github.jan.supabase.compose.auth.ui.annotations.AuthUiExperimental
import io.github.jan.supabase.compose.auth.ui.email.EmailField
import io.github.jan.supabase.compose.auth.ui.password.PasswordField

//@Preview(showBackground = true)
@OptIn(AuthUiExperimental::class, ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {

    val viewModel: AuthenticationViewModel = viewModel()
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    // UI for login screen

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        val (backButton, introAndSocial, content, actionButtons) = createRefs()

        // Back button
        IconButton(
            onClick = {
                navController.navigate("onboarding_screen")
            },
            modifier = Modifier.constrainAs(backButton) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        ) {
            Icon(
                Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = "Back to onBoarding"
            )
        }
        // Intro and social login
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .constrainAs(introAndSocial) {
                    top.linkTo(backButton.bottom, margin = 64.dp)
                    bottom.linkTo(content.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextWithLineBehind(text = "Log in", color = Colors.BrightMain)
                Text(
                    text = " to Chatbox",
                    style = Typography.titleMedium,
                )
            }
            Text(
                text = "Welcome back! Sign in using your social account or email to continue us",
                style = Typography.labelSmall,
                textAlign = TextAlign.Center,
                color = Colors.Grey,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(0.7f)
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            GoogleButton(true)
            Spacer(modifier = Modifier.padding(top = 16.dp))
            OrSplitter()
        }
        // Email and password Input
        Column(
            modifier = Modifier.constrainAs(content) {
                top.linkTo(introAndSocial.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            EmailField(
                value = email, onValueChange = { newValue ->
                    email = newValue
                },
                modifier = Modifier.fillMaxWidth(0.9f),
                label = { Text("Email") },
                placeholder = { Text("Enter your email",) },
                singleLine = true,
                colors = textFieldStyles(),
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Email,
                        contentDescription = "Email Icon",
                    )
                }
            )
            PasswordField(
                value = password,
                onValueChange = { newValue ->
                    password = newValue
                },
                modifier = Modifier.fillMaxWidth(0.9f),
                label = { Text("Password") },
                placeholder = { Text("Enter your Password") },
                singleLine = true,
                colors = textFieldStyles(),
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Lock,
                        contentDescription = "Password Icon",
                    )
                },
            )
        }
        // Login and forget password buttons
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.constrainAs(actionButtons) {
                bottom.linkTo(parent.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Button(
                onClick = {
                    viewModel.login(email.text, password.text)
                    navController.navigate("chat_screen")
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonColors(
                    containerColor = Colors.Main,
                    contentColor = Colors.BlueishWhite,
                    disabledContainerColor = Colors.OtherGrey,
                    disabledContentColor = Colors.BlueishWhite
                )
            ) {
                Text(
                    text = "Log in",
                    Modifier.padding(vertical = 8.dp),
                    fontSize = 16.sp,
                    style = Typography.labelMedium
                )
            }
            Text(
                text = "Forget password?",
                color = Colors.Main,
                style = Typography.labelSmall,
                modifier = Modifier.clickable {

                }
            )
        }
    }
}