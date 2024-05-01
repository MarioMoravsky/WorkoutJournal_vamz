package com.example.workoutjournal.Authentication

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.workoutjournal.databaza.UserEntity
import com.example.workoutjournal.databaza.UserRepositoryImpl
import kotlinx.coroutines.launch
import android.util.Log

class RegisterViewModel(private val userRepository: UserRepositoryImpl) : ViewModel() {
    val username = mutableStateOf("")
    val password = mutableStateOf("")
    val confirmPassword = mutableStateOf("")
    val name = mutableStateOf("")
    val age = mutableStateOf("")
    val weight = mutableStateOf("")
    val height = mutableStateOf("")

    suspend fun register() {
        try {
            if (password.value == confirmPassword.value) {
                val ageNumber = age.value.toIntOrNull()
                val weightNumber = weight.value.toDoubleOrNull()
                val heightNumber = height.value.toDoubleOrNull()

                if (ageNumber == null || weightNumber == null || heightNumber == null) {
                    // Zobraziť chybovú správu, že vstupné hodnoty nie sú platné čísla
                    return
                }

                val user = UserEntity(
                    id = 0,
                    name = name.value,
                    age = ageNumber,
                    weight = weightNumber,
                    height = heightNumber,
                    passwordHash = SecurityUtils.hashPassword(password.value)
                )
                userRepository.insertUser(user)
            } else {
                // Zobraziť chybovú správu, že heslá sa nezhodujú
            }
        } catch (e: Exception) {
            Log.e("RegisterException", "Exception during registration", e)
        }
    }

    @Composable
    fun RegisterScreen() {
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

            OutlinedTextField(
                value = confirmPassword.value,
                onValueChange = { confirmPassword.value = it },
                label = { Text("Confirm Password") }
            )

            OutlinedTextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Name") }
            )

            OutlinedTextField(
                value = age.value,
                onValueChange = { age.value = it },
                label = { Text("Age") }
            )

            OutlinedTextField(
                value = weight.value,
                onValueChange = { weight.value = it },
                label = { Text("Weight") }
            )

            OutlinedTextField(
                value = height.value,
                onValueChange = { height.value = it },
                label = { Text("Height") }
            )

            Button(onClick = { coroutineScope.launch { register()} }) {
                Text("Register")
            }
        }
    }
}