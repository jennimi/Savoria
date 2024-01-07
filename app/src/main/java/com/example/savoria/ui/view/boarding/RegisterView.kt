package com.example.savoria.ui.view.boarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.savoria.data.DataStoreManager
import com.example.savoria.ui.theme.lobster
import com.example.savoria.ui.view.search.SavoriaFont
import com.example.savoria.viewmodel.UserViewModel


//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun RegisterViewPreview() {
//    RegisterView({}, {}, {})
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterView(
    userViewModel: UserViewModel,
    dataStore: DataStoreManager,
    navController: NavController,
) {

    var username by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var birthdate by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var gender by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC5E3C8))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create Account",
            color = Color(0xFF2B2B2B),
            fontSize = 40.sp,
            fontFamily = lobster,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 20.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
                .background(Color.White, RoundedCornerShape(20.dp, 20.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 30.dp, bottom = 60.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "REGISTER",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = {
                        Text(text = "Username")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .padding(top = 10.dp, start = 30.dp, end = 30.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0x00FFFFFF),
                        unfocusedIndicatorColor = Color(0xFFA4A4A4),
                        unfocusedLabelColor = Color(0xFFA4A4A4),
                        focusedLabelColor = Color.Black,
                        cursorColor = Color.Black
                    )
                )

                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = {
                        Text(text = "Email")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .padding(top = 20.dp, start = 30.dp, end = 30.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0x00FFFFFF),
                        unfocusedIndicatorColor = Color(0xFFA4A4A4),
                        unfocusedLabelColor = Color(0xFFA4A4A4),
                        focusedLabelColor = Color.Black,
                        cursorColor = Color.Black
                    )
                )

                var isPasswordVisible by remember { mutableStateOf(false) }
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = {
                        Text(
                            text = "Password",
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .padding(top = 20.dp, start = 30.dp, end = 30.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0x00FFFFFF),
                        unfocusedIndicatorColor = Color(0xFFA4A4A4),
                        unfocusedLabelColor = Color(0xFFA4A4A4),
                        focusedLabelColor = Color.Black,
                        cursorColor = Color.Black
                    ),
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
                )

                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = {
                        Text(text = "Full Name")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .padding(top = 20.dp, start = 30.dp, end = 30.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0x00FFFFFF),
                        unfocusedIndicatorColor = Color(0xFFA4A4A4),
                        unfocusedLabelColor = Color(0xFFA4A4A4),
                        focusedLabelColor = Color.Black,
                        cursorColor = Color.Black
                    )
                )

                TextField(
                    value = birthdate,
                    onValueChange = { birthdate = it },
                    label = {
                        Text(text = "Birthdate (yyyy-mm-dd)")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text, // Adjust the keyboard type
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .padding(top = 20.dp, start = 30.dp, end = 30.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0x00FFFFFF),
                        unfocusedIndicatorColor = Color(0xFFA4A4A4),
                        unfocusedLabelColor = Color(0xFFA4A4A4),
                        focusedLabelColor = Color.Black,
                        cursorColor = Color.Black
                    )
                )

                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = {
                        Text(text = "Description")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text, // Adjust the keyboard type
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .padding(top = 20.dp, start = 30.dp, end = 30.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0x00FFFFFF),
                        unfocusedIndicatorColor = Color(0xFFA4A4A4),
                        unfocusedLabelColor = Color(0xFFA4A4A4),
                        focusedLabelColor = Color.Black,
                        cursorColor = Color.Black
                    )
                )

                TextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = {
                        Text(text = "Phone")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number, // Adjust the keyboard type
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .padding(top = 20.dp, start = 30.dp, end = 30.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0x00FFFFFF),
                        unfocusedIndicatorColor = Color(0xFFA4A4A4),
                        unfocusedLabelColor = Color(0xFFA4A4A4),
                        focusedLabelColor = Color.Black,
                        cursorColor = Color.Black
                    )
                )

                TextField(
                    value = gender,
                    onValueChange = { gender = it },
                    label = {
                        Text(text = "Gender")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text, // Adjust the keyboard type
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .padding(top = 20.dp, start = 30.dp, end = 30.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0x00FFFFFF),
                        unfocusedIndicatorColor = Color(0xFFA4A4A4),
                        unfocusedLabelColor = Color(0xFFA4A4A4),
                        focusedLabelColor = Color.Black,
                        cursorColor = Color.Black
                    )
                )

                Button (
                    onClick = { userViewModel.Register(
                        username, email, password, name, birthdate, description, phone, gender, navController, dataStore
                    ) }
                ) {
                    Text (text = "Register")
                }
            }
        }
    }
}

