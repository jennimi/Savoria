package com.example.savoria.ui.view.boarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import androidx.navigation.NavController
import com.example.savoria.R
import com.example.savoria.data.DataStoreManager
import com.example.savoria.ui.Screen
import com.example.savoria.ui.theme.inter
import com.example.savoria.viewmodel.AuthViewModel
import java.util.regex.Pattern

@Composable
fun LoginView(
    authViewModel: AuthViewModel,
    dataStore: DataStoreManager,
    navController: NavController,
) {
    val coroutineScope = rememberCoroutineScope()

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    var isEmailValid by rememberSaveable { mutableStateOf(true) }
    var isPasswordValid by rememberSaveable { mutableStateOf(true) }

    val context = LocalContext.current



    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Button(
            onClick = {
                navController.navigate(Screen.AppIntro.name)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0x00FFFFFF)
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_keyboard_backspace_24_green),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 10.dp)
                    .size(40.dp)

            )
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "WELCOME ",
                fontSize = 30.sp,
                letterSpacing = 1.sp,
                lineHeight = 40.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Log in to your account! \uD83E\uDD57️✨ ",
                fontSize = 14.sp,
                letterSpacing = 1.sp,
                lineHeight = 26.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 12.dp),
                fontWeight = FontWeight.Medium
            )

            CustomEmailField(
                value = email,
                onValueChanged = { email = it },
                text = "Email",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                isEmailValid = isEmailValid,
                colorContainer = Color(0xFFFFFFFF),
                colorBorder = Color(0xFFC5E3C8),
                colorCursor = Color(0xFF179B5B),
                colorLabel = Color(0xFFC5E3C8),
                colorLabelFocused = Color(0xFF179B5B),
                colorBorderFocused = Color(0xFF179B5B)
            )
            CustomPasswordField(
                value = password,
                onValueChanged = { password = it },
                text = "Password",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                isPasswordValid = isPasswordValid,
                colorContainer = Color(0xFFFFFFFF),
                colorBorder = Color(0xFFC5E3C8),
                colorCursor = Color(0xFF179B5B),
                colorLabel = Color(0xFFC5E3C8),
                colorLabelFocused = Color(0xFF179B5B),
                colorBorderFocused = Color(0xFF179B5B)
            )

            Button(
                onClick = {
                    isEmailValid = isValidEmail(email)
                    isPasswordValid = isValidPassword(password)

                if (isEmailValid && isPasswordValid){
                    authViewModel.Login(
                        email,
                        password,
                        context,
                        navController,
                        dataStore
                    )
                }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                enabled = email.isNotBlank()&&password.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF179B5B),
                    disabledContainerColor = Color(0xFFC5E3C8),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Login",
                    color = Color.White
                )
            }
        }
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
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+\$).{8,}\$"
    )
    return passwordPattern.matcher(password).matches()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomEmailField(
    value: String,
    onValueChanged: (String) -> Unit,
    text: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    isEmailValid: Boolean,
    colorContainer: Color,
    colorBorder: Color,
    colorCursor: Color,
    colorLabel: Color,
    colorLabelFocused: Color,
    colorBorderFocused: Color
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = text)},
        keyboardOptions = keyboardOptions,
        modifier = modifier,
        isError = !isEmailValid,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = colorContainer,
            unfocusedBorderColor = colorBorder,
            cursorColor = colorCursor,
            unfocusedLabelColor = colorLabel,
            focusedLabelColor = colorLabelFocused,
            focusedBorderColor = colorBorderFocused
        )
    )

    if (!isEmailValid){
        Text(
            text = "Invalid email format",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 4.dp),
            Color.Red
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPasswordField(
    value: String,
    onValueChanged: (String) -> Unit,
    text: String,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    isPasswordValid: Boolean,
    colorContainer: Color,
    colorBorder: Color,
    colorCursor: Color,
    colorLabel: Color,
    colorLabelFocused: Color,
    colorBorderFocused: Color
){

    var isPasswordVisible by remember { mutableStateOf(false)}

    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = text)},
        keyboardOptions = keyboardOptions,
        modifier = modifier,
        isError = !isPasswordValid,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(
                onClick = { isPasswordVisible = !isPasswordVisible }
            ) {
                Icon(
                    imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = colorContainer,
            unfocusedBorderColor = colorBorder,
            cursorColor = colorCursor,
            unfocusedLabelColor = colorLabel,
            focusedLabelColor = colorLabelFocused,
            focusedBorderColor = colorBorderFocused
        )
    )

    if (!isPasswordValid){
        Text(
            text = "Password must be 8 characters long and contain uppercase, lowercase, number, and special character",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 4.dp),
            Color.Red
        )
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun LoginPreview() {
//    val mockDBService = MyDBService()
//    val mockDBRepositories = MyDBRepositories(mockDBService)

//    LoginView()
//}