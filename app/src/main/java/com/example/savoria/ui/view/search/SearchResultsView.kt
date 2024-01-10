package com.example.savoria.ui.view.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.savoria.R
import com.example.savoria.model.RecipeResponse
import com.example.savoria.ui.Screen
import com.example.savoria.ui.theme.inter
import com.example.savoria.ui.view.home.RecipesContainer
import com.example.savoria.viewmodel.HomeViewModel
import retrofit2.Response

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchResultView(
    search: String,
    resultsListResponse: Response<List<RecipeResponse>>?,
    homeViewModel: HomeViewModel,
    navController: NavController
) {
    val resultsList: List<RecipeResponse>? = resultsListResponse?.body()
    val amount: Int = resultsList?.size ?: 0
    val searchkey: String = search

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 10.dp)
    ) {
        item {
            var search by rememberSaveable { mutableStateOf(" ") }
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                //searchbar
                Searchresult_Searchbar(
                    value = search,
                    onValueChanged = { search = it },
                    text = "search",
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
                //searchbar
            }
            Text(
                text = "Search result for '$searchkey' ",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontFamily = inter,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
            )
            Text(
                text = "found $amount relevant recipes ",
                fontSize = 16.sp,
                fontFamily = inter,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
            )
            RecipesContainer(
                allRecipes = resultsList,
                homeViewModel = homeViewModel,
                navController = navController
            )
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun SearchResultpreView() {
//    SearchResultView()
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Searchresult_Searchbar(
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
            Text(text = text)
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




