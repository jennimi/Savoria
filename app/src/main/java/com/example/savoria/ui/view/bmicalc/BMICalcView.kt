package com.example.savoria.ui.view.bmicalc

import android.webkit.ConsoleMessage
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.savoria.R
import com.example.savoria.ui.view.search.LobsterFont
import com.example.savoria.ui.view.search.SavoriaFont
import com.example.savoria.viewmodel.BMICalcViewModel
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun BMICalcView(){
    Column (
        modifier = Modifier
            .background(Color(0xFFBEDFB3))
    ){
        val viewModel = BMICalcViewModel()
        GenderButton(viewModel = viewModel)
        GenderImage()
        CalculateBMI(viewModel = viewModel)
    }

}

//untuk button tengahnya
@Composable
fun GenderButton(viewModel: BMICalcViewModel){
    var isManButtonPressed by remember { mutableStateOf(false) }
    var isWomanButtonPressed by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "BMI Calculator",
            color = Color.Black,
            fontFamily = LobsterFont,
            fontWeight = FontWeight.Normal,
            fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 28.dp)
        )
            Box(
                modifier = Modifier
                    .width(271.dp)
                    .height(35.dp)
                    .border(
                        2.dp,
                        color = Color(0xFF004A26),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(Color.Transparent),
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,

                ){
//                    MAN BUTTON
                    Button(
                        onClick = {
                            viewModel.setGender(isMan = true)
                            isManButtonPressed = true
                            isWomanButtonPressed = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isManButtonPressed) Color(0xFF179C5B) else Color.White,
                            contentColor = if (isManButtonPressed) Color.White else Color.Black
                        ),
                        modifier = Modifier
                            .width(110.dp)
                            .height(20.dp),
                        contentPadding = PaddingValues(0.dp)
                    )
                    {
                        Text(
                            text = "Man",
                            fontSize = 10.sp,
                        )
                    }

//                    WOMAN BUTTON
                    Button(
                        onClick = {
                            viewModel.setGender(isMan = false)
                            isManButtonPressed = false
                            isWomanButtonPressed = true
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isWomanButtonPressed) Color(0xFF179C5B) else Color.White,
                            contentColor = if (isWomanButtonPressed) Color.White else Color.Black
                        ),
                        modifier = Modifier
                            .width(110.dp)
                            .height(20.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = "Woman",
                            fontSize = 10.sp,
                        )
                    }
                }
            }
        }
    }

@Composable
fun GenderImage(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.diet_app_for_weight_loss2),
            contentDescription = "profileImage",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .clip(CircleShape)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculateBMI(viewModel: BMICalcViewModel){

    var showDialog by remember { mutableStateOf(false) }
    //    variabelnya
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
//    untuk error nilainya
    var isHeight by remember { mutableStateOf(false) }
    var isWeight by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(top = 75.dp)
    ) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .width(360.dp)
                .height(400.dp)
                .background(
                    Color.White,
                    shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                ),
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                HEIGHTTT
                Column {
                    Text(
                        text = "Your Height (cm)",
                        color = Color.Black,
                        fontFamily = SavoriaFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(bottom = 16.dp),
                    )
//        HEIGHT
                    TextField(
                        modifier = Modifier
                            .width(271.dp)
                            .height(51.dp),
                        value = viewModel.height.toString(),
                        onValueChange = {
                            viewModel.height = it.toInt()
                            isHeight = it.toFloat() ?: 0.0f <= 0
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(0xFF179C5B),
//                untuk hilangkan bordernya
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            textColor = Color.White
                        ),
                        shape = RoundedCornerShape(6.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        textStyle = LocalTextStyle.current.copy(
                            fontSize = 18.sp
                        )
                    )
                }

//                WEIGHT
                Column {
                    Text(
                        text = "Your Weight (kg)",
                        color = Color.Black,
                        fontFamily = SavoriaFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(top = 15.dp, bottom = 16.dp),
                    )
//        WEIGHT
                    TextField(
                        modifier = Modifier
                            .width(271.dp)
                            .height(51.dp),
                        value = viewModel.weight.toString(),
                        onValueChange = {
                            viewModel.weight = it.toInt()
                            isWeight = it.toFloat() ?: 0.0f <= 0
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(0xFF179C5B),
//                untuk hilangkan bordernya
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            textColor = Color.White
                        ),
                        shape = RoundedCornerShape(6.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        textStyle = LocalTextStyle.current.copy(
                            fontSize = 18.sp
                        )
                    )
                }

                ElevatedButton(
                    onClick = {
                         showDialog = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFBEDFB3)
                    ),
                    modifier = Modifier
                        .padding(top = 50.dp),
                    shape = RoundedCornerShape(6.dp),
                    enabled = viewModel.isGenderSelected
                ) {
                    Text(
                        text = "Calculate",
                        color = Color(0xFF024424)
                    )
                }
            }
            if (showDialog){
                val bmiCategoryMessage = viewModel.getBMICategoryMessage()
                val bmiResult = viewModel.calculateBMI()
                    CustomDialog (
                        bmiResult = bmiResult,
                        bmiCategoryMessage = bmiCategoryMessage,
                        onDismiss = {
                            showDialog = false
                        }
                    )
                }
            }
        }
    }

@Composable
fun CustomDialog(bmiResult: Float, bmiCategoryMessage: String, onDismiss: () -> Unit){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
        ){
            Card (
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .wrapContentHeight()
                    .size(width = 320.dp, height = 275.dp)
                    .padding(start = 20.dp, end = 20.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp),

                ){
                Column (
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxSize(),
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 25.dp),
                        text = "Your Result",
                        textAlign = TextAlign.Center,
                        fontFamily = SavoriaFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black

                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 23.dp),
                        text = "$bmiResult",
                        textAlign = TextAlign.Center,
                        fontFamily = SavoriaFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 40.sp,
                        color = Color(0xFF024424)
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, start = 12.dp, end = 12.dp),
                        text = bmiCategoryMessage ,
                        textAlign = TextAlign.Center,
                        fontFamily = SavoriaFont,
                        fontWeight = FontWeight.Medium,
                        fontSize = 11.sp,
                        color = Color.Black
                    )
                    TextButton(
                        onClick = {
                            onDismiss()},
                        modifier = Modifier
                            .padding(top = 5.dp),
                    ) {
                        Text(
                            text = "OK",
                            color = Color(0xFF024424),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(end = 10.dp),
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BMICalcPreview() {
//    InputText()
    BMICalcView()
//    CustomDialog()
//    Content()
}