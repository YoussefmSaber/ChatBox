package com.pixe.chatapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pixe.chatapp.data.network.SupabaseClient.supabase
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.IDToken
import io.github.jan.supabase.gotrue.user.UserSession
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthenticationViewModel : ViewModel() {

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                supabase.auth.signInWith(Email) {
                    this.email = email
                    this.password = password
                }
                Log.d("Login operation", "login Successful")
                val currentSession = supabase.auth.currentSessionOrNull()
                Log.d("Login operation", "Current Session: ${currentSession?.accessToken}")
            } catch (e: Exception) {
                Log.d("Login operation", "Error while login: $e")
            }
        }
    }


    fun loginUsingToken(token: String): Boolean {
        var isLoginSuccess = false
        viewModelScope.launch(Dispatchers.IO) {
            try {
                supabase.auth.signInWith(IDToken) {
                    idToken = token
                    provider = Google
                }
                Log.d("Login operation", "login Successful")
                val currentSession = supabase.auth.currentSessionOrNull()
                Log.d("Login operation", "Current Session: ${currentSession?.accessToken}")
                isLoginSuccess = true
            } catch (e: Exception) {
                Log.d("Login operation", "Login using token failed: $e")
                isLoginSuccess = false
            }
        }
        return isLoginSuccess
    }

    fun getUserAccessToken(): UserSession? {
        var token: UserSession? = null
        viewModelScope.launch {
            try {
                token = supabase.auth.currentSessionOrNull()
            }   catch (e: Exception) {
                Log.d("Login Operation", "Get user access token: $e")
            }
        }

        return token
    }
}