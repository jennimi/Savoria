package com.example.savoria.ui.view.create

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.savoria.R
import com.example.savoria.ui.theme.inter

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CreateRecipeView() {
    var RecipeName by rememberSaveable { mutableStateOf("") }
    var Caption by rememberSaveable { mutableStateOf("") }
    var Calories by rememberSaveable { mutableStateOf("") }
    var Time by rememberSaveable { mutableStateOf("") }
    var Serving by rememberSaveable { mutableStateOf("") }
    var Ingredient by rememberSaveable { mutableStateOf("") }
    var search by rememberSaveable { mutableStateOf("") }
    var selectedimage by remember { mutableStateOf<Uri?>(null) }
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedimage = uri }
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF079f59))
                    .height(50.dp)
            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.baseline_keyboard_backspace_24),
//                    contentDescription = "Back",
//                    modifier = Modifier
//                        .weight(1f)
//                        .fillMaxSize()
//                )
                Text(
                    text = "Create new recipe",
                    textAlign = TextAlign.Center,
                    fontSize = 28.sp,
                    color = Color.White,
                    modifier = Modifier.weight(9f)
                        .fillMaxHeight(),
                    fontFamily = inter
                )
            }
        }
        item {
            Column(
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 5.dp)
            ) {
                Text(
                    text = "add image",
                    fontFamily = inter
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        onClick = {
                            singlePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .height(200.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC6E4C9))
                    ) {
                        if (selectedimage == null) {
//                            Image(
//                                painter = painterResource(id = R.drawable.round_add_24),
//                                contentDescription = "add",
//                                modifier = Modifier
//                                    .background(color = Color.White, shape = CircleShape)
//                            )
                        } else {
                            AsyncImage(
                                model = selectedimage,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    }
                }
            }
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 5.dp)
            ) {
                Text(
                    text = "Recipe Name",
                    fontFamily = inter
                )
                CreateTextfield(
                    value = RecipeName,
                    onValueChanged = {RecipeName = it},
                    text = "Enter Recipe Name",
                    keyboardOption = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )
            }
        }

        item {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 5.dp)
            ) {
                Text(
                    text = "Caption",
                    fontFamily = inter
                )
                CreateTextfield(
                    value = Caption,
                    onValueChanged = {Caption = it},
                    text = "Enter Caption",
                    keyboardOption = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )
            }
        }
        item {
            Row(
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 5.dp),
            ) {
                Column(
                    modifier = Modifier
                        .weight(3f)
                        .padding(end = 5.dp),
                ) {
                    Text(
                        text = "calories",
                        fontFamily = inter
                    )
                    CreateTextfield(
                        value = Calories,
                        onValueChanged = {Calories = it},
                        text = "e.g 200",
                        keyboardOption = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(3f)
                        .padding(horizontal = 5.dp),
                ) {
                    Text(
                        text = "times",
                        fontFamily = inter
                    )
                    CreateTextfield(
                        value = Time,
                        onValueChanged = {Time = it},
                        text = "e.g.120",
                        keyboardOption = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(3f)
                        .padding(start = 5.dp),
                ) {
                    Text(
                        text = "servings",
                        fontFamily = inter
                    )
                    CreateTextfield(
                        value = Serving,
                        onValueChanged = {Serving = it},
                        text = "e.g. 1.5",
                        keyboardOption = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )
                }
            }
        }
        item {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 5.dp)
            ) {
                Text(
                    text = "ingredients",
                    fontFamily = inter
                )
                CreateTextfield(
                    value = Time,
                    onValueChanged = {Time = it},
                    text = "Enter ingredients e.g.\n- ...\n- ...\n- ...",
                    keyboardOption = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )
            }
        }
        item {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 5.dp)
            ) {
                Text(
                    text = "steps",
                    fontFamily = inter
                )
                CreateTextfield(
                    value = Time,
                    onValueChanged = {Time = it},
                    text = "Enter steps e.g.\n" +
                            "1. ...\n" +
                            "2. ...\n" +
                            "3. ...",
                    keyboardOption = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTextfield(
    value: String,
    onValueChanged: (String) -> Unit,
    text: String,
    keyboardOption: KeyboardOptions,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .background(Color(0xFFC6E4C9))
    ) {
        TextField(
            value = value,
            onValueChange = onValueChanged,
            placeholder = {
                Text(
                    text = text,
                    fontFamily = inter
                )
            },
            keyboardOptions = keyboardOption,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFC6E4C9),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black
            ),
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreateRecipePreview() {
    CreateRecipeView()
}