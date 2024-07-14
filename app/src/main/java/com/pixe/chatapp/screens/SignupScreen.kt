package com.pixe.chatapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.pixe.chatapp.ui.theme.Colors
import com.pixe.chatapp.ui.theme.Typography
import com.pixe.chatapp.ui.theme.textFieldStyles
import com.pixe.chatapp.viewModel.AuthenticationViewModel
import com.pixe.chatapp.widgets.TextWithLineBehind
import io.github.jan.supabase.compose.auth.ui.annotations.AuthUiExperimental
import io.github.jan.supabase.compose.auth.ui.email.EmailField
import io.github.jan.supabase.compose.auth.ui.password.PasswordField

@OptIn(AuthUiExperimental::class, ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun SignupScreen(navController: NavController) {

    val viewModel: AuthenticationViewModel = viewModel()
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
    var isError by remember { mutableStateOf(false) }

    // UI for Sign up screen
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
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
                Text(
                    text = "Sign up with ",
                    style = Typography.titleMedium,
                )
                TextWithLineBehind(text = "Email", color = Colors.BrightMain)
            }
            Text(
                text = "Get chatting with friends and family today by signing up for our chat app!",
                style = Typography.labelSmall,
                textAlign = TextAlign.Center,
                color = Colors.Grey,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(0.7f)
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
        }
        // Email and password Input
        Column(
            modifier = Modifier.constrainAs(content) {
                top.linkTo(introAndSocial.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            // Name Field
            TextField(
                value = name,
                onValueChange = { text ->
                    name = text
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 16.dp),
                label = { Text("Name") },
                placeholder = { Text("Enter your name") },
                singleLine = true,
                colors = textFieldStyles(),
                leadingIcon = {
                    Icon(
                        Icons.Outlined.AccountCircle,
                        contentDescription = "Email Icon",
                    )
                }
            )
            // Email Field
            EmailField(
                value = email, onValueChange = { newValue ->
                    email = newValue
                },
                modifier = Modifier.fillMaxWidth(0.9f),
                label = { Text("Email") },
                placeholder = { Text("Enter your email") },
                singleLine = true,
                colors = textFieldStyles(),
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Email,
                        contentDescription = "Email Icon",
                    )
                }
            )
            // Password Field
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
            // Confirm Password Field
            PasswordField(
                value = confirmPassword,
                onValueChange = { newValue ->
                    confirmPassword = newValue
                    isError = confirmPassword.text != password.text
                    if (confirmPassword.text == "")
                        isError = false
                },
                modifier = Modifier.fillMaxWidth(0.9f),
                label = { Text("Confirm password") },
                placeholder = { Text("Enter your Password Again") },
                singleLine = true,
                colors = textFieldStyles(),
                isError = isError,
                supportingText = {
                    if (isError)
                        Text(
                            "Passwords do not match",
                            color = Colors.Error
                        )
                },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Lock,
                        contentDescription = "Password Icon",
                    )
                },
            )
        }
        // Sign up button
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
                    navController.navigate("login_screen")
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
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
                    text = "Sign up",
                    Modifier.padding(vertical = 9.dp),
                    fontSize = 16.sp,
                    style = Typography.labelMedium
                )
            }
        }
    }

}