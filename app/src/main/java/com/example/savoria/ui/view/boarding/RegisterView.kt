package com.example.savoria.ui.view.boarding

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import coil.compose.AsyncImage
import com.example.savoria.R
import com.example.savoria.data.DataStoreManager
import com.example.savoria.ui.Screen
import com.example.savoria.ui.theme.lobster
import com.example.savoria.viewmodel.AuthViewModel


//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun RegisterViewPreview() {
//    RegisterView()
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterView(
    authViewModel: AuthViewModel,
    dataStore: DataStoreManager,
    navController: NavController,
    context: Context
) {

    var username by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var birthdate by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var gender by rememberSaveable { mutableStateOf("") }

    var selectedimage by remember { mutableStateOf<Uri?>(null) }
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedimage = uri }
    )

    val finalImageUri: Uri = rememberSaveable { selectedimage ?: Uri.EMPTY }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "REGISTER",
                fontSize = 30.sp,
                letterSpacing = 1.sp,
                lineHeight = 40.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Come and join us with Savoria! \uD83E\uDD57️✨ ",
                fontSize = 14.sp,
                letterSpacing = 1.sp,
                lineHeight = 26.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 12.dp),
                fontWeight = FontWeight.Medium
            )

            Button(
                onClick = {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .border(3.dp, Color(0xFF179B5B), RoundedCornerShape(10.dp)),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0x00C6E4C9))
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    if (selectedimage == null) {
                        Image(
                            painter = painterResource(id = R.drawable.round_add_24),
                            contentDescription = "Add",
                            modifier = Modifier
                                .background(color = Color.White, shape = CircleShape)
                        )
                    } else {
                        AsyncImage(
                            model = selectedimage,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    Text(
                        text = "Upload your profile picture here",
                        color = Color(0xFF179B5B),
                        modifier = Modifier
                            .padding(top = 8.dp)
                    )
                }
            }

            OutlinedTextField(
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
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFFFFFFF),
                    unfocusedLabelColor = Color(0xFFC5E3C8),
                    focusedLabelColor = Color(0xFF179B5B),
                    cursorColor = Color(0xFF179B5B),
                    unfocusedBorderColor = Color(0xFFC5E3C8),
                    focusedBorderColor = Color(0xFF179B5B)
                )
            )

            OutlinedTextField(
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
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFFFFFFF),
                    unfocusedLabelColor = Color(0xFFC5E3C8),
                    focusedLabelColor = Color(0xFF179B5B),
                    cursorColor = Color(0xFF179B5B),
                    unfocusedBorderColor = Color(0xFFC5E3C8),
                    focusedBorderColor = Color(0xFF179B5B)
                )
            )

            var isPasswordVisible by remember { mutableStateOf(false) }
            OutlinedTextField(
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
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFFFFFFF),
                    unfocusedLabelColor = Color(0xFFC5E3C8),
                    focusedLabelColor = Color(0xFF179B5B),
                    cursorColor = Color(0xFF179B5B),
                    unfocusedBorderColor = Color(0xFFC5E3C8),
                    focusedBorderColor = Color(0xFF179B5B)
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

            OutlinedTextField(
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
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFFFFFFF),
                    unfocusedLabelColor = Color(0xFFC5E3C8),
                    focusedLabelColor = Color(0xFF179B5B),
                    cursorColor = Color(0xFF179B5B),
                    unfocusedBorderColor = Color(0xFFC5E3C8),
                    focusedBorderColor = Color(0xFF179B5B)
                )
            )

            OutlinedTextField(
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
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFFFFFFF),
                    unfocusedLabelColor = Color(0xFFC5E3C8),
                    focusedLabelColor = Color(0xFF179B5B),
                    cursorColor = Color(0xFF179B5B),
                    unfocusedBorderColor = Color(0xFFC5E3C8),
                    focusedBorderColor = Color(0xFF179B5B)
                )
            )

            OutlinedTextField(
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
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFFFFFFF),
                    unfocusedLabelColor = Color(0xFFC5E3C8),
                    focusedLabelColor = Color(0xFF179B5B),
                    cursorColor = Color(0xFF179B5B),
                    unfocusedBorderColor = Color(0xFFC5E3C8),
                    focusedBorderColor = Color(0xFF179B5B)
                )
            )

            OutlinedTextField(
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
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFFFFFFF),
                    unfocusedLabelColor = Color(0xFFC5E3C8),
                    focusedLabelColor = Color(0xFF179B5B),
                    cursorColor = Color(0xFF179B5B),
                    unfocusedBorderColor = Color(0xFFC5E3C8),
                    focusedBorderColor = Color(0xFF179B5B)
                )
            )

            OutlinedTextField(
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
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFFFFFFF),
                    unfocusedLabelColor = Color(0xFFC5E3C8),
                    focusedLabelColor = Color(0xFF179B5B),
                    cursorColor = Color(0xFF179B5B),
                    unfocusedBorderColor = Color(0xFFC5E3C8),
                    focusedBorderColor = Color(0xFF179B5B)
                )
            )

            Button(
                onClick = {
                    selectedimage?.let {
                        authViewModel.Register(
                            username,
                            email,
                            password,
                            name,
                            birthdate,
                            description,
                            phone,
                            gender,
                            it,
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
                    text = "Register",
                    color = Color.White
                )
            }
        }
    }
}

