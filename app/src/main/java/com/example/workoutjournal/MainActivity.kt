package com.example.workoutjournal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.workoutjournal.Authentication.LoginViewModel
import com.example.workoutjournal.Authentication.RegisterViewModel
import com.example.workoutjournal.databaza.DatabaseHelper
import com.example.workoutjournal.databaza.UserRepositoryImpl
import com.example.workoutjournal.ui.theme.WorkoutJournalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutJournalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val databaseHelper = DatabaseHelper.getInstance(this)
                    val userRepository = UserRepositoryImpl(databaseHelper.userDao())
                    val registerViewModel = RegisterViewModel(userRepository)
                    val loginViewModel = LoginViewModel(userRepository)
                    Navigation(loginViewModel, registerViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Composable
fun Navigation(loginViewModel: LoginViewModel, registerViewModel: RegisterViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "login") {
        composable("login") { loginViewModel.LoginScreen(navController) } // Use the LoginScreen function from the LoginViewModel
        composable("register") { registerViewModel.RegisterScreen() } // Use the RegisterScreen function from the RegisterViewModel
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WorkoutJournalTheme {
        Greeting("Android")
    }
}