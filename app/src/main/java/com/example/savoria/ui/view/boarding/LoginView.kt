package com.example.savoria.ui.view.boarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.savoria.ui.repository.MyDBRepositories
import com.example.savoria.ui.service.MyDBService
import com.example.savoria.ui.theme.inter
import com.example.savoria.ui.theme.lobster
import kotlinx.coroutines.launch
import java.util.regex.Pattern

import java.net.HttpURLConnection

@Composable
fun LoginView(
    toHome: () -> Unit,
    myDBRepositories: MyDBRepositories
) {
    val coroutineScope = rememberCoroutineScope()

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    var isEmailValid by rememberSaveable { mutableStateOf(true) }
    var isPasswordValid by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text (
            text = "Welcome to Savoria",
            style = TextStyle(
                fontFamily = inter,
                fontSize = 28.sp
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        CustomEmailField(
            value = email,
            onValueChange = { email = it },
            text = "Email",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            isEmailValid = isEmailValid
        )
        CustomPasswordField(
            value = password,
            onValueChange = { password = it },
            text = "Password",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            isPasswordValid = isPasswordValid
        )

        Button(
            onClick = {
                isEmailValid = isValidEmail(email)
                isPasswordValid = isValidPassword(password)

                if (isEmailValid && isPasswordValid) {
                    coroutineScope.launch {
                        val loginResult = myDBRepositories.login(email, password)

                        // Handle the result accordingly
                        if (loginResult.status.toInt() == HttpURLConnection.HTTP_OK) {
                            toHome()
                        } else {
                            val errorMessage = loginResult.message
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
        ) {
            Text(text = "Submit")
        }
        // AAAAAA adding stuff
    }
}

// Function to validate email using regex
fun isValidEmail(email: String): Boolean {
    val emailPattern = Pattern.compile(
        "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$",
        Pattern.CASE_INSENSITIVE
    )
    return emailPattern.matcher(email).matches()
}

// Function to validate password
fun isValidPassword(password: String): Boolean {
    val passwordPattern = Pattern.compile(
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,}\$"
    )
    return passwordPattern.matcher(password).matches()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomEmailField(
    value: String,
    onValueChange: (String) -> Unit,
    text: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    isEmailValid: Boolean
) {
    OutlinedTextField (
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = text)},
        keyboardOptions = keyboardOptions,
        modifier = modifier,
        isError = !isEmailValid
    )

    if (!isEmailValid) {
        Text (
            text = "Invalid Email format",
            color = Color.Red,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 4.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    text: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    isPasswordValid: Boolean
) {

    var isPasswordVisible by remember { mutableStateOf(false) }

    OutlinedTextField (
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = text)},
        keyboardOptions = keyboardOptions,
        modifier = modifier,
        isError = !isPasswordValid,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(
                onClick = { isPasswordVisible = !isPasswordVisible }
            ) {
            }
        },
    )

    if (!isPasswordValid) {
        Text (
            text = "Password must be 8 characters long and contain uppercase, lowercase, number, and special character",
            color = Color.Red,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 4.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPreview() {
//    val mockDBService = MyDBService()
//    val mockDBRepositories = MyDBRepositories(mockDBService)

//    LoginView({}, )
}