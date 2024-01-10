package com.example.savoria.ui.view.profile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.savoria.ui.view.search.SavoriaFont
import com.example.savoria.R
import com.example.savoria.model.RecipeResponse
import com.example.savoria.model.User
import com.example.savoria.model.UserResponse
import com.example.savoria.ui.view.home.RecipeContent
import com.example.savoria.ui.view.home.RecipesContainer
import com.example.savoria.viewmodel.HomeUIState
import com.example.savoria.viewmodel.HomeViewModel
import com.example.savoria.viewmodel.ProfileUIState
import com.example.savoria.viewmodel.ProfileViewModel
import retrofit2.Response

@Composable
fun ProfileView(
    profileViewModel: ProfileViewModel,
    toSettings: () -> Unit,
    homeViewModel: HomeViewModel,
    navController: NavController
){
    val profileUIState: ProfileUIState = profileViewModel.profileUIState
    var currentUserDetails: Response<UserResponse>? = null
    var currentUser: Response<User>? = null
    var recipeByUserResponse: Response<List<RecipeResponse>>? = null
    var recipeSavedResponse: Response<List<RecipeResponse>>? = null

    when (profileUIState) {
        is ProfileUIState.Success -> {
            currentUserDetails = profileUIState.userInSessionDetails
            currentUser = profileUIState.userInSession
            recipeByUserResponse = profileUIState.recipesByUser
            recipeSavedResponse = profileUIState.recipesSaved
        }
        is ProfileUIState.Error -> {
            Log.e("AllProfile", "Error fetching data")
        }
        ProfileUIState.Loading -> {
            Log.d("AllProfile", "Loading...")
        }

        else -> {}
    }

    val recipeByUser = recipeByUserResponse?.body()
    val recipeSaved = recipeSavedResponse?.body()

    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            item {
                AllProfile( currentUserDetails, toSettings )
            }
            item {
                var selectedTabIndex by remember { mutableStateOf(1)}
                val tabs = listOf(
                    "MyRecipe",
                    "FavoriteRecipe"
                )

                Column (
                    modifier = Modifier.fillMaxWidth(),
//              verticalArrangement = Arrangement.Center,
//              horizontalAlignment = Alignment.CenterHorizontally
                ){
                    TabRow(
                        selectedTabIndex = selectedTabIndex,
                        indicator = {},
                        modifier = Modifier
//                            .padding(horizontal = 90.dp)
                    ) {
                        tabs.forEachIndexed { index, title ->
                            Tab(
                                selected = selectedTabIndex == index,
                                onClick = { selectedTabIndex = index },
                                modifier = Modifier
                                    .selectable(selected = selectedTabIndex == index) {}
                                    .padding(10.dp)
                            ) {
                                if (title == "MyRecipe") {
                                    if (selectedTabIndex == index) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.grid_colored),
                                            contentDescription = "Grid Icon",
                                            modifier = Modifier
                                                .width(20.dp)
                                                .height(20.dp)
                                        )
                                    } else {
                                        Icon(
                                            painter = painterResource(id = R.drawable.grid_black),
                                            contentDescription = "Grid Icon",
                                            modifier = Modifier
                                                .width(20.dp)
                                                .height(20.dp)
                                        )
                                    }
                                } else {
                                    if (selectedTabIndex == index) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.favorite_colored),
                                            contentDescription = "Favorite Icon",
                                            modifier = Modifier
                                                .width(20.dp)
                                                .height(20.dp)

                                        )
                                    } else {
                                        Icon(
                                            painter = painterResource(id = R.drawable.love_circled),
                                            contentDescription = "Favorite Icon",
                                            modifier = Modifier
                                                .width(20.dp)
                                                .height(20.dp)

                                        )
                                    }
                                }
                            }
                        }
                    }
                    when (selectedTabIndex) {
                        0-> RecipesContainer(allRecipes = recipeByUser, homeViewModel = homeViewModel, navController = navController)
                        1-> RecipesContainer(allRecipes = recipeSaved, homeViewModel = homeViewModel, navController = navController)
                    }
//                    ContentPost()

//                    Row (
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceAround,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.data_grid),
//                            contentDescription = "Grid Icon",
//                            modifier = Modifier
//                                .width(20.dp)
//                                .height(20.dp)
//
//                        )
//                        Icon(
//                            painter = painterResource(id = R.drawable.love_circled),
//                            contentDescription = "Favorite Icon",
//                            modifier = Modifier
//                                .width(20.dp)
//                                .height(20.dp)
//                        )
//                    }
                }
            }
//            item {
//                garisNav()
//                ContentPost()
//            }
        }
    }
}

@Composable
fun AllProfile(
    currentUserDetails: Response<UserResponse>?,
    toSettings: () -> Unit,
){

    val name: String? = currentUserDetails?.body()?.name
    val username: String? = currentUserDetails?.body()?.username
    val caption: String? = currentUserDetails?.body()?.description
    val followers: Int? = currentUserDetails?.body()?.followers_count
    val followings: Int? = currentUserDetails?.body()?.followings_count

    Column (
        modifier = Modifier
            .padding(24.dp)
    ){
        Row {
            Text(
                text = "Profile",
                color = Color.Black,
                fontFamily = SavoriaFont,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 13.dp)
            )
//            Button(
//                onClick = toSettings
//            ) {
//                Text(text="Settings")
//            }
        }
        
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
                text = "$name",
                color = Color.Black,
                fontFamily = SavoriaFont,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
            Button(
                onClick = toSettings,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF179C5B)),
                modifier = Modifier
                    .width(67.67.dp)
                    .height(18.dp),
                contentPadding = PaddingValues(0.dp),
            ) {
                Text(
                    text = "Settings",
                    color = Color.White,
                    fontFamily = SavoriaFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 9.sp
                )

            }

        }
        Text(text = "@$username",
            color = Color.Black,
            fontFamily = SavoriaFont,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 3.dp, top = 3.dp)
        )
        Text(text = "$caption",
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
            // posts
            Text(
                text = "3",
                fontSize = 9.sp,
                fontFamily = SavoriaFont,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF024424)
            )
            Text(
                text = "Posts",
                fontSize = 9.sp,
                color = Color(0xFF024424)
            )
            // following
            Text(
                text = "$followings",
                fontSize = 9.sp,
                fontFamily = SavoriaFont,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF024424),
                modifier = Modifier
                    .clickable {

                    }
            )
            Text(
                text = "Following",
                fontSize = 9.sp,
                color = Color(0xFF024424)
            )
            // followers
            Text(
                text = "$followers",
                fontSize = 9.sp,
                fontFamily = SavoriaFont,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF024424)
            )
            Text(
                text = "Followers",
                fontSize = 9.sp,
                color = Color(0xFF024424)
            )
        }
    }
}

//        untuk iconnya like feeds dan sebagainya
@Composable
fun iconProfile(){
    Column (
        modifier = Modifier.fillMaxWidth(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.data_grid),
                contentDescription = "Grid Icon",
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)

            )
            Icon(
                painter = painterResource(id = R.drawable.love_circled),
                contentDescription = "Grid Icon",
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)

            )
            Icon(
                painter = painterResource(id = R.drawable.add_bookmark),
                contentDescription = "Grid Icon",
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)

            )
        }
    }
}


// untuk garis navigasinya
@Composable
fun garisNav(){
    Column (
        modifier = Modifier
    ){
        Row {
            Image(
                painter = painterResource(id = R.drawable.rectangle_52) ,
                contentDescription = "",
                modifier = Modifier
                    .width(160.dp)
                    .height(2.dp)
            )
        }
    }
}


//        untuk satu content feednya
@Composable
fun ContentPost(){
    Card (
        modifier = Modifier,
        shape = RectangleShape
    ){
            Box (
                modifier = Modifier
                    .height(156.dp)
                    .width(120.dp)
                    .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter,
                ){
                    Image(
                        painter = painterResource(id = R.drawable.burger2),
                        contentDescription = "post",
                        contentScale = ContentScale.Crop
                    )
                    Column (
                        modifier = Modifier
                            .background(
                                Color.White.copy(alpha = 0.5f),
                                shape = RectangleShape
                            )
                            .size(120.dp, 34.dp),
                        verticalArrangement = Arrangement.Center, 
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "Veggie Delight \n" +
                                    "Salad",
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = SavoriaFont,
                            modifier = Modifier
                                .fillMaxWidth()
                                .graphicsLayer(alpha = 1f)
                                .padding(start = 10.dp),
                        )
                }
            }
        }
    }



//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ProfileViewPreview(){
//    ProfileView({})
//}