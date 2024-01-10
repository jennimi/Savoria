package com.example.savoria.ui.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.savoria.model.RecipeResponse
import com.example.savoria.model.UserResponse
import com.example.savoria.ui.Screen
import com.example.savoria.ui.theme.inter
import com.example.savoria.viewmodel.RecipeDetailViewModel
import retrofit2.Response


@Composable
fun RecipeView(
    navController: NavController,
    recipeResponse: Response<RecipeResponse>,
    userResponse: Response<UserResponse>,
    recipeDetailViewModel: RecipeDetailViewModel
) {
    val recipe: RecipeResponse = recipeResponse.body()!!
    val user: UserResponse = userResponse.body()!!

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        item {
            Box() {
//                Image(
//                    painter = painterResource(R.drawable.burger1),
//                    contentDescription = "Menu",
//                    contentScale = ContentScale.FillWidth,
//                    modifier = Modifier
//                        .fillMaxSize()
//                )
                LoadImageCustom(
                    url = recipe.image,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.FillWidth
                )
                FloatingActionButton(
                    onClick = {
                        navController.popBackStack()
                    },
                    containerColor = Color(0xFFFFFFFF),
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 30.dp)
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
                            onClick = { },
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
                                    text = "${recipe.saved_by_users_count}",
                                    color = Color.White,
                                    fontFamily = inter,
                                )
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = "Favorite",
                                    modifier = Modifier.size(16.dp),
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
                                .clickable {
                                    // USER VIEW
                                }
                        ) {
                            Text(
                                // get user name
                                text = "by ${user.name}",
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
                            text = recipe.recipe_name,
                            fontWeight = FontWeight.Bold,
                            fontFamily = inter,
                            fontSize = 30.sp,
                            modifier = Modifier.widthIn(max=200.dp)
                        )
                        Text(
                            text = recipe.caption,
                            fontFamily = inter,
                            fontSize = 15.sp,
                            modifier = Modifier.widthIn(max=200.dp)
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    var isLiked by remember { mutableStateOf(recipe.favorited) }
                    FloatingActionButton(
                        onClick = {
                            if (isLiked) {
                                recipeDetailViewModel.unfavoriteRecipe(recipe.id)
                            } else {
                                recipeDetailViewModel.favoriteRecipe(recipe.id)
                            }
                            isLiked = !isLiked },
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
                        onClick = {
                            navController.navigate(Screen.CommentsView.name + "/" + recipe.id)
                        },
                        containerColor = Color(0xFF079f59),
                        shape = CircleShape
                    ) {
                        Row() {
                            Icon(
                                imageVector = Icons.Outlined.Comment,
                                contentDescription = "Comment",
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
                            text = "${recipe.time}",
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
                            text = "${recipe.calorie}",
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
                            text = "${recipe.servings}",
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
                    text = recipe.ingredients,
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
                    text = recipe.steps,
                    fontFamily = inter,
                    fontSize = 21.sp,
                    modifier = Modifier
                        .padding(5.dp)
                )
            }
        }
    }
}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun RecipePreview() {
//    RecipeView()
//}