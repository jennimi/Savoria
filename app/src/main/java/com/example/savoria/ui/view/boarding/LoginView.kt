package com.example.savoria.ui.view.boarding

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.savoria.ui.theme.inter
import com.example.savoria.ui.theme.lobster

@Composable
fun LoginView() {
    Column(

    ) {
        Text (
            text = "Use this Inter Font",
            style = TextStyle(
                fontFamily = inter,
                fontWeight = FontWeight(700),
            )
        )
        Text (
            text = "Use this Lobster Font",
            style = TextStyle(
                fontFamily = lobster
            )
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPreview() {
    LoginView()
}