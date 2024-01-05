package com.example.savoria.ui.view.boarding

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AppIntroView(
    toLogin: () -> Unit,
    toRegister: () -> Unit,
) {
    Column {
        Text(text = "Welcome to Savoria")
        Button (
            onClick = toLogin,
        ) {
            Text(text = "Login")
        }

        Button (
            onClick = toRegister,
        ) {
            Text(text = "Register")
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AppIntroPreview() {
    AppIntroView({}, {})
}