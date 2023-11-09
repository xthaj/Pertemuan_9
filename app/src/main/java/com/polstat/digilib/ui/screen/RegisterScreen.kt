package com.polstat.digilib.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
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
fun RegisterScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Register",
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(16.dp)
        )

        Column(modifier = Modifier.padding(24.dp)) {
            // name
            TextField(
                value = "",
                onValueChange = { /* TODO */ },
                label = { Text(text = "Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            TextField(
                value = "",
                onValueChange = { /* TODO */ },
                label = { Text(text = "Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            TextField(
                value = "",
                onValueChange = { /* TODO */ },
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            TextField(
                value = "",
                onValueChange = { /* TODO */ },
                label = { Text(text = "Confirm Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }

        // submit
        Button(
            onClick = {
                // TODO
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Register")
        }

        Text(
            text = "Already have an account? Login",
            color = Color.Gray,
//            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
fun PreviewRegisterScreen(){
    RegisterScreen()
}