package com.polstat.digilib.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RegisterScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Register Screen",
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(16.dp)
        )

        // Add input fields, buttons, or other UI elements for the registration screen

        Button(
            onClick = {
                // Perform the registration action here, and navigate if needed
                // For example, you can call a registration function and navigate on success
                navController.navigate("welcomeScreen") // Navigating back to the welcome screen
            }
        ) {
            Text(text = "Register")
        }
    }
}