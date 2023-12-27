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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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


@Composable
fun Content(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
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
            Column (
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxHeight()
            ){
                Text(
                    text = "Your Height",
                    color = Color.Black,
                    fontFamily = SavoriaFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 10.dp)

                )
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(271.dp)
                        .height(65.dp)
                        .padding(top = 16.dp)
                        .background(
                            color = Color(0xFF179C5B),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.CenterStart
                ){
                    Text(
                        text = "Your Height in (cm)",
                        color = Color.White,
                        fontFamily = SavoriaFont,
                        fontWeight = FontWeight.Medium,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(start = 15.dp)

                    )
                }
                Text(
                    text = "Your Weight",
                    color = Color.Black,
                    fontFamily = SavoriaFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(271.dp)
                        .height(65.dp)
                        .padding(top = 16.dp)
                        .background(
                            color = Color(0xFF179C5B),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.CenterStart
                ){
                    Text(
                        text = "Your Weight in (kg)",
                        color = Color.White,
                        fontFamily = SavoriaFont,
                        fontWeight = FontWeight.Medium,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
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
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BMICalcPreview() {
    BMICalcView()
//    ImageContent()
//    Content()
//    CenterButton()

}