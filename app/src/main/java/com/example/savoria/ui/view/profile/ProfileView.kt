package com.example.savoria.ui.view.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.savoria.ui.view.search.SavoriaFont
import com.example.savoria.R

@Composable
fun ProfileView(){
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            AllProfile()
            iconProfile()
            ContentPost()

        }

    }
}

@Composable
fun AllProfile(){
//    untuk info profile nya
    Column (
        modifier = Modifier
            .padding(24.dp)
    ){
        Text(
            text = "Profile",
            color = Color.Black,
            fontFamily = SavoriaFont,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 13.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.burger2),
            contentDescription = "profileImage",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Row (
            modifier = Modifier
                .padding(top = 9.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Lewis Carrol😳",
                color = Color.Black,
                fontFamily = SavoriaFont,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF179C5B)),
                modifier = Modifier
                    .width(67.67.dp)
                    .height(18.dp),
                contentPadding = PaddingValues(0.dp),
            ) {
                Text(
                    text = "Edit Profile",
                    color = Color.White,
                    fontFamily = SavoriaFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 9.sp
                )

            }

        }
        Text(text = "@lewcar",
            color = Color.Black,
            fontFamily = SavoriaFont,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 3.dp, top = 3.dp)
        )
        Text(text = "I eat everything from street food to fine dining, from spicy to sweet🌶️🍦",
            color = Color.Black,
            fontFamily = SavoriaFont,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
        )
        Row (
            modifier = Modifier
                .padding(top = 7.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp),

        ){
            Text(
                text = "3",
                fontSize = 9.sp,
                fontFamily = SavoriaFont,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Posts",
                fontSize = 9.sp
            )
            Text(
                text = "48",
                fontSize = 9.sp,
                fontFamily = SavoriaFont,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Following",
                fontSize = 9.sp
            )
            Text(
                text = "1250",
                fontSize = 9.sp,
                fontFamily = SavoriaFont,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Followers",
                fontSize = 9.sp
            )
        }
    }
}

//        untuk iconnya like feeds dan sebagainya
@Composable
fun iconProfile(){
    Row (
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ){
        Icon(
            painter = painterResource(id = R.drawable.baseline_grid_view_24),
            contentDescription = "Grid Icon",
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)

        )
//        Icon(
//            painter = painterResource(id = R.drawable.baseline_favorite_24),
//            contentDescription = "Grid Icon",
//            modifier = Modifier
//                .width(20.dp)
//                .height(20.dp)
//
//        )
//        Icon(
//            painter = painterResource(id = R.drawable.round_save_24),
//            contentDescription = "Grid Icon",
//            modifier = Modifier
//                .width(20.dp)
//                .height(20.dp)
//
//        )
    }
}

//        untuk content feednya
@Composable
fun ContentPost(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(156.dp),
        horizontalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.burger1),
            contentDescription = "Image Content",
            modifier = Modifier
                .weight(1f)
                .height(156.dp),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(id = R.drawable.burger1),
            contentDescription = "Image Content",
            modifier = Modifier
                .weight(1f)
                .height(156.dp),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(id = R.drawable.burger1),
            contentDescription = "Image Content",
            modifier = Modifier
                .weight(1f)
                .height(156.dp),
            contentScale = ContentScale.Crop
        )

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileViewPreview(){
    ProfileView()
}