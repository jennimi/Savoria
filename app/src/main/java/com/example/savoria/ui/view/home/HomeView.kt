package com.example.savoria.ui.view.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.savoria.R
import com.example.savoria.data.DataStoreManager
import com.example.savoria.model.RecipeResponse
import com.example.savoria.model.User
import com.example.savoria.ui.Screen
import com.example.savoria.ui.theme.inter
import com.example.savoria.ui.theme.lobster
import com.example.savoria.viewmodel.HomeUIState
import com.example.savoria.viewmodel.HomeViewModel
import retrofit2.Response
import com.example.savoria.ui.view.home.RecipeContent as RecipeContent

@Composable
fun HomeView(
    homeViewModel: HomeViewModel,
    navController: NavController,
) {

    val homeViewModel1: HomeUIState = homeViewModel.homeUIState
    var currentUser: Response<User>? = null
    var allRecipesBody: Response<List<RecipeResponse>>? = null
    var followRecipesBody: Response<List<RecipeResponse>>? = null


    when (homeViewModel1) {
        is HomeUIState.Success -> {
            currentUser = homeViewModel.userInSession
            allRecipesBody = homeViewModel.allRecipe
            followRecipesBody = homeViewModel.followRecipe
        }
        is HomeUIState.Error -> {
        }
        HomeUIState.Loading -> {
        }
    }

    val username: String? = currentUser?.body()?.name
    val allRecipes: List<RecipeResponse>? = allRecipesBody?.body()
    val followRecipes: List<RecipeResponse>? = followRecipesBody?.body()

    Column {
        LazyColumn {
            item {
                Column(
                    modifier = Modifier
                        .background(
                            color = Color(0xFF079f59),
                            shape = RoundedCornerShape(0.dp, 0.dp, 40.dp, 40.dp)
                        )
                        .padding(horizontal = 32.dp)
                        .clip(shape = RoundedCornerShape(0.dp, 0.dp, 40.dp, 40.dp))
                ) {
                    //App name
                    Text(
                        text = "Savoria",
                        fontFamily = lobster,
                        fontSize = 32.sp,
                        color = Color.White,
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(top = 10.dp)
                    )
                    //App name
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 2.dp, bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        //welcome text

                        Text(
                            text = "Hey there $username! \nready to explore for \nsome recipe?",
                            fontSize = 16.sp,
                            color = Color.White,
//                            modifier = Modifier
//                                .weight(6f),
                            letterSpacing = 1.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.Medium
                        )
                        //welcome text

                        //profile picture
//                        Image(
//                            painter = painterResource(id = R.drawable.round_person_24),
//                            contentDescription = "profile_pic",
//                            modifier = Modifier
//                                .size(128.dp)
//                                .weight(2f)
//                        )

                        LoadImageCustom(
                            url = "${currentUser?.body()?.profile_picture}",
                            modifier = Modifier
                                .width(80.dp)
                                .height(80.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        //profile picture
                    }
                    var search by rememberSaveable { mutableStateOf("") }
                    Row(
                        modifier = Modifier.padding(bottom = 15.dp, top = 8.dp)
                    ) {
                        //searchbar
                        Searchbar(
                            value = search,
                            onValueChanged = { search = it; },
                            text = "search any recipe",
                            keyboardOption = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                            modifier = Modifier
                                .weight(0.75f)
                        )
                        //searchbar

                        //filter
                        Image(
                            painter = painterResource(id = R.drawable.outline_filter_alt_24), // Replace with your filter icon
                            contentDescription = "Filter Icon",
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .background(Color(0xFFFFFFFF), shape = CircleShape)
                                .weight(0.2f)
                                .height(50.dp)
                                .clickable {
                                    navController.navigate(Screen.ResultsView.name + "/" + search)
                                }
                        )
                        //filter
                    }

                }
            }
            item {
                //following and for you
                var selectedTabIndex by remember { mutableStateOf(1) }
                val tabs = listOf(
                    "Following",
                    "For you"
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    TabRow(
                        selectedTabIndex = selectedTabIndex,
                        indicator = {},
                        modifier = Modifier
                            .padding(horizontal = 90.dp)
                    ) {
                        tabs.forEachIndexed { index, title ->
                            Tab(
                                selected = selectedTabIndex == index,
                                onClick = { selectedTabIndex = index },
                                modifier = Modifier
                                    .selectable(selected = selectedTabIndex == index) {}
                                    .padding(10.dp)
                            ) {
                                Text(
                                    text = title,
                                    color = if (selectedTabIndex == index) Color.White else Color.Black,
                                    modifier = Modifier
                                        .background(
                                            if (selectedTabIndex == index) Color(0xFF079f59) else Color.Transparent,
                                            shape = CircleShape
                                        )
                                        .padding(8.dp),
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    // calling Content for following and for you
                    when (selectedTabIndex) {
                        0 -> RecipesContainer(followRecipes, homeViewModel, navController)
                        1 -> RecipesContainer(allRecipes, homeViewModel, navController)
                    }
                }
                //following and for you
            }
        }
    }
}


//searchbar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Searchbar(
    value: String,
    onValueChanged: (String) -> Unit,
    text: String,
    keyboardOption: KeyboardOptions,
    modifier: Modifier = Modifier,
) {
    TextField(
        shape = CircleShape,
        value = value,
        onValueChange = onValueChanged,
        placeholder = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxHeight()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = "search_icon"
                )
                Text(text = text)
            }
        },
        keyboardOptions = keyboardOption,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black,
            containerColor = Color.White
        ),
        modifier = modifier
    )
}

//searchbar

//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun Following(
//    allRecipes: List<RecipeResponse>?,
//    homeViewModel: HomeViewModel,
//    navController: NavController
//) {
//    LazyVerticalStaggeredGrid(
//        columns = StaggeredGridCells.Fixed(2),
//        verticalItemSpacing = 4.dp,
//        horizontalArrangement = Arrangement.spacedBy(4.dp),
//        content = {
//            if (allRecipes != null) {
//                items(allRecipes) {recipe ->
//                    RecipeContent(recipe, homeViewModel)
//                }
//            }
//        },
//        modifier = Modifier
//            .fillMaxSize()
//            .height(1000.dp)
//    )
//}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipesContainer(
    allRecipes: List<RecipeResponse>?,
    homeViewModel: HomeViewModel,
    navController: NavController
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        content = {
            if (allRecipes != null) {
                items(allRecipes) {recipe ->
                    RecipeContent(recipe, homeViewModel, navController)
                }
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .height(1000.dp)
            .padding(horizontal = 14.dp)
            .background(Color(0x00FFFFFF))
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeContent(
    recipe: RecipeResponse,
    homeViewModel: HomeViewModel,
    navController: NavController
) {
    var isLiked by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .clickable {
                navController.navigate(Screen.RecipeView.name + "/" + recipe.id.toString())
            }
            .background(Color(0x00FFFFFF))
            .clip(RoundedCornerShape(16.dp)),
        shape = RectangleShape,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color(0xFFFFFFFF))
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                //image
//                Image(
//                    painter = painterResource(id = R.drawable.burger1),
//                    contentDescription = "image",
//                    contentScale = ContentScale.FillBounds,
//                    modifier = Modifier
//                        .clip(RoundedCornerShape(16.dp))
//                )
                LoadImageCustom(
                    url = recipe.image,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .height(200.dp),
                    contentScale = ContentScale.FillBounds
                )

                //image

                //Like Button
                FloatingActionButton(
                    onClick = {
                        if (!isLiked) {
                            homeViewModel.favoriteRecipe(recipe.id)
                        } else {
                            homeViewModel.unfavoriteRecipe(recipe.id)
                        }
                        isLiked = !isLiked
                    },
                    containerColor = Color(0xFF079f59),
                    modifier = Modifier
                        .height(30.dp)
                        .align(alignment = BottomEnd)
                        .padding(end = 10.dp, bottom = 10.dp)
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
                        Spacer(modifier = Modifier.width(2.dp))
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Favorite",
                            modifier = Modifier.size(16.dp),
                            tint = if (isLiked) {
                                Color(0xFFFF1100)
                            } else {
                                Color(0xFFFFFFFF)
                            }
                        )
                    }
                }
                //like button
            }
            //title
            Text(
                text = recipe.recipe_name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontFamily = inter,
                lineHeight = 20.sp
            )
            //title

            //caption
            Text(
                text = recipe.caption,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 15.dp),
                fontSize = 15.sp,
                color = Color.Black,
                fontFamily = inter,
                lineHeight = 20.sp
            )
            //caption
        }
    }
}

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
fun LoadImageCustom(
    url: String? = null,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    contentScale: ContentScale
) {
    Box(modifier = modifier) {
        GlideImage(
            model = url?: "https://www.foodandwine.com/thmb/pwFie7NRkq4SXMDJU6QKnUKlaoI=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Ultimate-Veggie-Burgers-FT-Recipe-0821-5d7532c53a924a7298d2175cf1d4219f.jpg",
            contentDescription = "Sample Image",
            contentScale = contentScale,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewHome() {
//    HomeView({}, {}, {})
//}
