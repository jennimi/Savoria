package com.example.savoria.ui.view.bmicalc

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.savoria.R
import com.example.savoria.ui.view.search.LobsterFont
import com.example.savoria.ui.view.search.SavoriaFont

@Composable
fun BMIResultView(){
    Column (
        modifier = Modifier
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(
            text = "Your Result",
            color = Color.Black,
            fontFamily = SavoriaFont,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 28.dp),
            textAlign = TextAlign.Center
        )
        ResultContent()
        InfoCard()
        Button()
    }

}


@Composable
fun ResultContent(){
    Column (
        modifier = Modifier
            .padding(top = 50.dp)

    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .width(271.dp)
                .height(191.dp)
                .background(
                    Color(0xFF179C5B),
                    shape = RoundedCornerShape(12.dp)
                )
        ){
            Row {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.dumbells),
                        contentDescription ="gambar dumbells",
                        modifier = Modifier
                            .width(88.dp)
                            .height(227.dp)
                            .padding(start = 20.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text ="Your Current BMI is",
                        color = Color.White,
                        fontFamily = SavoriaFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(bottom = 28.dp)
                    )
                    Text(
                        text ="18.5",
                        color = Color.White,
                        fontFamily = SavoriaFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 40.sp,
                        modifier = Modifier.padding(bottom = 28.dp)
                    )
                    Text(
                        text ="(Overweight)",
                        color = Color.White,
                        fontFamily = SavoriaFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                    )
                }
            }
        }
    }
}

@Composable
fun InfoCard(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 68.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box (
            modifier = Modifier
                .width(271.dp)
                .height(76.dp)
                .shadow(elevation = 2.dp)
                .background(
                    color = Color(0xFFF3F3F3),
                    shape = RoundedCornerShape(3.dp)
                ),
            contentAlignment = Alignment.CenterStart,
        ){
            Text(
                text = "A BMI of 18.5 indicates that you are at a" +
                    "healthy weight for your height. By maintaining a" +
                    "healthy weight, you lower your risk of developing" +
                    "serious health problems.",
                fontFamily = SavoriaFont,
                fontWeight = FontWeight.Medium,
                fontSize = 11.sp,
                textAlign = TextAlign.Center,
            )

        }
    }
}

@Composable
fun Button(){
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
                text = "Recalculate",
                color = Color(0xFF024424)

            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BMIResultPreview(){
//    ResultContent()
//    InfoCard()
//    Button()
    BMIResultView()

}

