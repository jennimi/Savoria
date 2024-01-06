package com.example.savoria.ui.view.home

import android.util.Size
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Comment
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.KeyboardBackspace
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.material.icons.outlined.RoomService
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.savoria.R
import com.example.savoria.ui.theme.inter


@Composable
fun RecipeView() {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.White)
    ) {
        item {
            Box() {
                Image(
                    painter = painterResource(R.drawable.burger1),
                    contentDescription = "Menu",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxSize()
                )
                FloatingActionButton(
                    onClick = { },
                    containerColor = Color(0xFFFFFFFF),
                    shape = CircleShape,
                    modifier = Modifier.padding(start = 20.dp, top = 30.dp)
                        .size(45.dp)
                ) {
                    Row() {
                        Icon(
                            imageVector =Icons.Outlined.KeyboardBackspace,
                            contentDescription = "back",
                            tint = Color(0xFF079f59)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        FloatingActionButton(
                            onClick = { /* Handle like button click */ },
                            containerColor = Color(0xFF079f59),
                            modifier = Modifier
                                .padding(start = 14.dp)
                                .height(30.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .widthIn(min = 50.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "1",
                                    color = Color.White,
                                    fontFamily = inter,
                                )
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = "Favorite",
                                    modifier = Modifier.size(20.dp),
                                    tint =
                                        Color(0xFFFFFFFF)
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .padding(end = 14.dp)
                                .height(30.dp)
                                .background(Color(0xFF079f59), shape = CircleShape)
                        ) {
                            Text(
                                text = "by abdawheghbeahibdkja",
                                color = Color.White,
                                fontFamily = inter,
                                modifier = Modifier
                                    .padding(horizontal = 5.dp)
                                    .widthIn(min = 100.dp)
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .background(
                                Color.White,
                                shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp)
                            )
                            .clip(shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp))
                            .fillMaxWidth()
                            .height(30.dp)
                    ) {

                    }
                }
            }
        }
        item {
            Column(
                modifier = Modifier
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(40.dp, 40.dp, 0.dp, 0.dp)
                    )
                    .clip(shape = RoundedCornerShape(40.dp, 40.dp, 0.dp, 0.dp))
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(

                    ) {
                        Text(
                            text = "Burger",
                            fontWeight = FontWeight.Bold,
                            fontFamily = inter,
                            fontSize = 30.sp,
                            modifier = Modifier.widthIn(max=200.dp)
                        )
                        Text(
                            text = "with lots of meat",
                            fontFamily = inter,
                            fontSize = 15.sp,
                            modifier = Modifier.widthIn(max=200.dp)
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    var isLiked by remember { mutableStateOf(false) }
                    FloatingActionButton(
                        onClick = { isLiked = !isLiked },
                        containerColor = Color(0xFF079f59),
                        shape = CircleShape,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    ) {
                        Row() {
                            Icon(
                                imageVector =
                                if (isLiked) {
                                    Icons.Outlined.Favorite
                                } else {
                                    Icons.Outlined.FavoriteBorder
                                },
                                contentDescription = "Favorite",
                                tint = Color(0xFFFFFFFF)
                            )
                        }
                    }
                    FloatingActionButton(
                        onClick = { /* Handle like button click */ },
                        containerColor = Color(0xFF079f59),
                        shape = CircleShape
                    ) {
                        Row() {
                            Icon(
                                imageVector = Icons.Outlined.Comment,
                                contentDescription = "comment",
                                tint = Color(0xFFFFFFFF)
                            )
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(horizontal = 50.dp, vertical = 5.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .height(135.dp)
                            .width(75.dp)
                            .background(Color(0xFF079f59), shape = CircleShape)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .height(50.dp)
                                .width(50.dp)
                                .background(Color(0xFFFFFFFF), shape = CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.AccessTime,
                                contentDescription = "Time",
                            )

                        }
                        Text(
                            text = "35",
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp,
                            fontFamily = inter,
                            modifier = Modifier
                                .padding(top = 4.dp)
                        )
                        Text(
                            text = "Min",
                            color = Color.White,
                            fontSize = 15.sp,
                            fontFamily = inter,
                            modifier = Modifier
                                .padding(top = 4.dp)
                        )
                    }


                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .height(135.dp)
                            .width(75.dp)
                            .background(Color(0xFF079f59), shape = CircleShape)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .height(50.dp)
                                .width(50.dp)
                                .background(Color(0xFFFFFFFF), shape = CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.LocalFireDepartment,
                                contentDescription = "calories",
                            )

                        }
                        Text(
                            text = "305",
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp,
                            fontFamily = inter,
                            modifier = Modifier
                                .padding(top = 4.dp)
                        )
                        Text(
                            text = "cal",
                            color = Color.White,
                            fontSize = 15.sp,
                            fontFamily = inter,
                            modifier = Modifier
                                .padding(top = 4.dp)
                        )
                    }


                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .height(135.dp)
                            .width(75.dp)
                            .background(Color(0xFF079f59), shape = CircleShape)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .height(50.dp)
                                .width(50.dp)
                                .background(Color(0xFFFFFFFF), shape = CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.RoomService,
                                contentDescription = "serving",
                            )

                        }
                        Text(
                            text = "1",
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp,
                            fontFamily = inter,
                            modifier = Modifier
                                .padding(top = 4.dp)
                        )
                        Text(
                            text = "serving",
                            color = Color.White,
                            fontSize = 15.sp,
                            fontFamily = inter,
                            modifier = Modifier
                                .padding(top = 4.dp)
                        )
                    }

                }
                Text(
                    text = "Ingredients",
                    fontWeight = FontWeight.Bold,
                    fontFamily = inter,
                    fontSize = 21.sp,
                    modifier = Modifier
                        .padding(5.dp)
                )
                Text(
                    text = "1\n2\n3\n4\n5\n6\n7\n8\n9\n10",
                    fontFamily = inter,
                    fontSize = 21.sp,
                    modifier = Modifier
                        .padding(5.dp)
                )
                Text(
                    text = "Directions",
                    fontWeight = FontWeight.Bold,
                    fontFamily = inter,
                    fontSize = 21.sp,
                    modifier = Modifier
                        .padding(5.dp)
                )
                Text(
                    text = "1. In a large bowl, mix all the pasta ingredients together with a wooden spoon until combined, use your hands towards the end if need be." +
                            "\n2. Then sprinkle some flour onto your kitchen surface, take the pasta mixture and start kneading it with the palm of your hand. Knead for approximately 10 minutes, until the dough feels smooth to touch. You’ll know when it’s done if it has a slight shine to it and gently bounces back when you touch it with your fingers. If the dough feels too wet and sticks to your hands, add a little bit more flour and a sprinkle of semolina.",
                    fontFamily = inter,
                    fontSize = 21.sp,
                    modifier = Modifier
                        .padding(5.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecipePreview() {
    RecipeView()
}