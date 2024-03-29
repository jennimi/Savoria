package com.example.savoria.ui.view.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.savoria.R
import com.example.savoria.model.Category
import com.example.savoria.model.RecipeResponse
import com.example.savoria.ui.Screen
import com.example.savoria.ui.theme.inter
import com.example.savoria.ui.view.home.LoadImageCustom
import com.example.savoria.ui.view.home.RecipeContent
import com.example.savoria.viewmodel.SearchUIState
import com.example.savoria.viewmodel.SearchViewModel
import retrofit2.Response


@Composable
fun SearchView(
    searchViewModel: SearchViewModel,
    navController: NavController,
) {

    val searchViewModel1: SearchUIState = searchViewModel.searchUIState
    var allCategoriesResponse: Response<List<Category>>? = null
    var top5RecipesResponse: Response<List<RecipeResponse>>? = null

    when (searchViewModel1) {
        is SearchUIState.Success -> {
            allCategoriesResponse = searchViewModel.allCategories
            top5RecipesResponse = searchViewModel.top5Recipes
        }

        is SearchUIState.Error -> {
        }

        SearchUIState.Loading -> {
        }
    }

    val allCategories: List<Category>? = allCategoriesResponse?.body()
    val top5RecipeResponse: List<RecipeResponse>? = top5RecipesResponse?.body()

    Column {
        var search by rememberSaveable { mutableStateOf(" ") }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Searchview_searchbar(
                value = search,
                onValueChanged = { search = it; },
                text = "Search Recipe",
                keyboardOption = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .weight(0.7f)
                    .padding(vertical = 20.dp)
                    .padding(start = 24.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.outline_filter_alt_24), // Replace with your filter icon
                contentDescription = "Filter Icon",
                modifier = Modifier
                    .height(50.dp)
                    .padding(end = 24.dp, start = 10.dp)
                    .weight(0.2f)
                    .clickable {
                        navController.navigate(Screen.ResultsView.name + "/" + search)
                    }
            )
        }

        Text(
            text = "Most Liked",
            fontSize = 24.sp,
            fontFamily = inter,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 24.dp)
        )
        LazyRow(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            top5RecipeResponse?.size?.let {
                items(it) {
                    val recipe: RecipeResponse = top5RecipeResponse[it]
                    ContentCard(recipe)
                }
            }
        }
        Text(
            text = "Categories",
            fontSize = 24.sp,
            fontFamily = inter,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 5.dp)
        )
//        LazyRow(
//            modifier = Modifier.padding(horizontal = 20.dp)
//        ){
//            if (allCategories != null) {
//                items(allCategories) {category ->
//                    Categories_search(category)
//                }
//            }
//        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            if (allCategories != null) {
                items(allCategories.size) { category ->
                    val currentCategory = allCategories[category]
                    Categories_search(currentCategory, navController)
                }
            }
        }
//        Text(
//            text = "ingredients",
//            fontSize = 24.sp,
//            fontFamily = inter,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier
//                .padding(horizontal = 24.dp, vertical = 5.dp)
//        )
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(3),
//            modifier = Modifier.padding(horizontal = 20.dp)
//        ){
//            items(10){
//                Ingredients_search()
//            }
//        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Searchview_searchbar(
    value: String,
    onValueChanged: (String) -> Unit,
    text: String,
    keyboardOption: KeyboardOptions,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        shape = CircleShape,
        value = value,
        onValueChange = onValueChanged,
        label = {
            Text(
                text = text,
                fontFamily = inter,
                color = Color.Black
            )
        },
        keyboardOptions = keyboardOption,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color(0xFFFFFFFF),
            cursorColor = Color.Black,
            unfocusedBorderColor = Color(0xFFC5E3C8),
            focusedBorderColor = (Color(0xFF179B5B))
        ),
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun Categories_search(
    category: Category,
    navController: NavController
) {
    val profileid = R.drawable.burger1
    Card(
        modifier = Modifier
            .width(115.dp)
            .height(170.dp)
            .padding(10.dp)
            .clickable {
                navController.navigate(Screen.CategoryView.name + "/" + category.id)
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(color = Color(0xFF079f59))
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(2f)
            ) {
//                Image(
//                    painter = painterResource(id = profileid),
//                    contentDescription = "pic",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .size(80.dp)
//                        .padding(5.dp, 3.dp)
//                        .clip(CircleShape)
//                )
                LoadImageCustom(
                    url = category.category_image,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(5.dp, 3.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text = category.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
                    .padding(start = 15.dp, end = 15.dp, bottom = 10.dp),
                fontSize = 15.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

        }

    }
}

@Composable
fun Ingredients_search() {
    val profileid = R.drawable.burger2
    Card(
        modifier = Modifier
            .width(115.dp)
            .height(145.dp)
            .padding(10.dp)
            .border(1.dp, color = Color.LightGray)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(color = Color(0xFF079f59))
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(2f)
            ) {
                Image(
                    painter = painterResource(id = profileid),
                    contentDescription = "pic",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(5.dp, 3.dp)
                        .clip(CircleShape)
                )
            }

            Text(
                text = "ingredientname",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f),
                fontSize = 15.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

        }

    }
}

@Composable
fun ContentCard(
    recipe: RecipeResponse
) {
    val imageData = painterResource(id = R.drawable.burger2)
    val title = "Burger"
    Card(
        modifier = Modifier
            .width(230.dp)
            .height(165.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(16.dp)),

        ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
        ) {
            LoadImageCustom(
                url = recipe.image,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x37FFFFFF), RoundedCornerShape(16.dp)),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = recipe.recipe_name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = inter,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    color = Color.Black,
                    maxLines = 1
                )

            }
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun SearchpreView(){
//    SearchView()
//}
