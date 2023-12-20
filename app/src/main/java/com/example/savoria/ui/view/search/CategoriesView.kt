package com.example.savoria.ui.view.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.savoria.R

@Composable
fun CategoriesView(){

//    ini supaya bisa di scroll
//    percobaan
    val listState = rememberLazyListState()

    Column(

        modifier = Modifier
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
//        ini belum jadi list state nya
//        state = listState
    ) {
        Text(
            text = "Categories",
            color = Color.Black,
            fontFamily = LobsterFont,
            fontWeight = FontWeight.Normal,
            fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 28.dp)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(28.dp)
        ){
            items(8){ index ->
                OneCategoriesCard()
            }
        }
    }
}


//function untuk satu cardnya
@Composable
fun OneCategoriesCard(){
    Card(
//        ini untuk shadow nya di card
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(20.dp),
//        ini untuk mengatur padding dan ukurannya
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .size(width = 312.dp, height = 70.88.dp),
//        ini untuk mengatur warnanya sendiri
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF179C5B)
        ),
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ){
            Column {
                Column (
                ){
                    Text(
                        text = "Fruits",
                        color = Color.White,
                        fontFamily = SavoriaFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Fresh, Vibrant, and vitamin-packed goodness",
                        color = Color.White,
                        fontFamily = SavoriaFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 8.sp
                    )
                }
            }
            Column {
                Image(
                    painter = painterResource(
                        id = R.drawable._e25dff88d4fb38ce1083d3493efcc96
                    ),
                    contentDescription = "Fruit",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(width = 48.dp, height = 48.dp)
                        .clip(CircleShape)
                )
            }
        }
    }
}

//ini untuk simpan semua font inter nya
val SavoriaFont = FontFamily(
    Font(R.font.inter_extralight, FontWeight.ExtraLight),
    Font(R.font.inter_thin, FontWeight.Thin),
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_semibold, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold),
    Font(R.font.inter_extrabold, FontWeight.ExtraBold),
    Font(R.font.inter_black, FontWeight.Black),
)

//ini untuk simpan font Lobster nya
val LobsterFont = FontFamily(
    Font(R.font.lobster_regular, FontWeight.Normal)
)


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CategoriesViewPreview(){
//    OneCategoriesCard()
    CategoriesView()
}

