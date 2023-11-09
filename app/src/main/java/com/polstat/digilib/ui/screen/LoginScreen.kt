package com.polstat.digilib.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. Default Icon
        Icon(
            imageVector = Icons.Default.AccountCircle, // You can replace this with your desired icon
            contentDescription = null, // Provide a meaningful description
            modifier = Modifier.size(96.dp) // Adjust the size as needed
        )

        Text(
            text = "Login Screen",
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(16.dp)
        )

        Column {
            // 2. Username Field
            TextField(
                value = "",
                onValueChange = { /* Handle username input */ },
                label = { Text(text = "Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            // 3. Password Field
            TextField(
                value = "",
                onValueChange = { /* Handle password input */ },
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation(), // Mask password input
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            // 4. Remember Me Checkbox
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = false, // Initialize with the desired value
                    onCheckedChange = { /* Handle checkbox state change */ }
                )
                Text(text = "Remember Me", modifier = Modifier.padding(start = 8.dp)
                )
            }

        }

        // 5. Submit Button
        Button(
            onClick = {
                // Perform the login action here, and navigate if needed
                // For example, you can call a login function and navigate on success
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Login")
        }

        Text(
            text = "Don't have an account? Sign up here",
            color = Color.Gray, // You can adjust the color as needed
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
fun PreviewLoginScreen(){
    LoginScreen()
}