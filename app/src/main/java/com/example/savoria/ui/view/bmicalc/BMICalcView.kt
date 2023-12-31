package com.example.savoria.ui.view.bmicalc

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
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
import com.example.savoria.R
import com.example.savoria.ui.view.search.LobsterFont
import com.example.savoria.ui.view.search.SavoriaFont
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun BMICalcView(){
    Column (
        modifier = Modifier
            .background(Color(0xFFBEDFB3))
    ){
        CenterButton()
        ImageContent()
        Content()
    }

}

//untuk button tengahnya
@Composable
fun CenterButton(){
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
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White),
                        modifier = Modifier
                            .width(110.dp)
                            .height(20.dp),
                        contentPadding = PaddingValues(0.dp)
                    )
                    {
                        Text(
                            text = "Man",
                            fontSize = 10.sp,
                            color = Color.Black,
                            modifier = Modifier
                        )
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF179C5B)),
                        modifier = Modifier
                            .width(110.dp)
                            .height(20.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = "Woman",
                            fontSize = 10.sp

                        )
                    }
                }
            }
        }
    }

@Composable
fun ImageContent(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.young_woman_leaning_on_something_with_hands_folded),
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
fun Content(){
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
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Your Height",
                    color = Color.Black,
                    fontFamily = SavoriaFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 16.dp),
                )
//        HEIGHT
                TextField(
                    modifier = Modifier
                        .width(271.dp)
                        .height(51.dp),
                    value = height,
                    onValueChange = {
                        height = it
                        isHeight = it.toFloat() ?: 0.0f <= 0
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFF179C5B),
//                untuk hilangkan bordernya
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(6.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = {
                        Text(
                            text = "Your Height in (cm)",
                            color = Color.White
                        )
                    }
                )
                Text(
                    text = "Your Height",
                    color = Color.Black,
                    fontFamily = SavoriaFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 15.dp, bottom = 16.dp),
                )
//        HEIGHT
                TextField(
                    modifier = Modifier
                        .width(271.dp)
                        .height(51.dp),
                    value = height,
                    onValueChange = {
                        height = it
                        isHeight = it.toFloat() ?: 0.0f <= 0
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFF179C5B),
//                untuk hilangkan bordernya
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(6.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    label = {
                        Text(
                            text = "Your Height in (cm)",
                            color = Color.White
                        )
                    }
                )
                ElevatedButton(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFBEDFB3)),
                    modifier = Modifier
                        .padding(top = 50.dp),
                    shape = RoundedCornerShape(6.dp),
                ) {
                    Text(
                        text = "Calculate",
                        color = Color(0xFF024424)

                    )
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
//    Content()


}