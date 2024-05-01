package com.example.workoutjournal.Authentication

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.workoutjournal.databaza.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    val username = mutableStateOf("")
    val password = mutableStateOf("")

    suspend fun login() {
        try {
            val enteredUsername = username.value
            val enteredPassword = password.value
            val hashedPassword = SecurityUtils.hashPassword(enteredPassword)

            val user = withContext(Dispatchers.IO) {
                userRepository.getUserByUsername(enteredUsername)
            }

            if (user != null) {
                if (user.passwordHash == hashedPassword) {
                    // Login successful, navigate to the next screen
                } else {

                    Log.e("LoginError", "Invalid password")
                }
            } else {

                Log.e("LoginError", "User not found")
            }
        } catch (e: Exception) {
            Log.e("LoginException", "Exception during login", e)
        }
    }
    @Composable
    fun LoginScreen(navController: NavController) {
        val coroutineScope = rememberCoroutineScope()
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(
                value = username.value,
                onValueChange = { username.value = it },
                label = { Text("Username") }
            )

            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") }
            )

            Button(onClick = { coroutineScope.launch { login() } }) {
                Text("Login")
            }

            TextButton(onClick = { navController.navigate("register") }) {
                Text("Create account")
            }
        }
    }
}