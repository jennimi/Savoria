package com.example.savoria.ui.view.boarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.savoria.R

@Composable
fun AppIntroView(
    toLogin: () -> Unit,
    toRegister: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(
            painter = painterResource(id = R.drawable.savoria),
            contentDescription = "Savoria",
            Modifier
                .padding(top = 50.dp, bottom = 80.dp)
                .size(250.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SAVORIA",
                fontSize = 30.sp,
                letterSpacing = 1.sp,
                lineHeight = 40.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Where Every Meal Tells a Story! \uD83E\uDD57️✨ ",
                fontSize = 14.sp,
                letterSpacing = 1.sp,
                lineHeight = 26.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 12.dp),
                fontWeight = FontWeight.Medium
            )

            Button(
                onClick = toLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF179B5B)
                )
            ) {
                Text(text = "Login")
            }

            Button(
                onClick = toRegister,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0282EA)
                )
            ) {
                Text(text = "Register")
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AppIntroPreview() {
    AppIntroView({}, {})
}