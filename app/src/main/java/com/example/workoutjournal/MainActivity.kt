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
                    val viewModel = RegisterViewModel(userRepository)
                    viewModel.RegisterScreen()
                    //val viewModel = LoginViewModel()
                    //viewModel.LoginScreen(viewModel)
                    //Greeting("Android")
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WorkoutJournalTheme {
        Greeting("Android")
    }
}