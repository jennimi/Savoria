package com.example.savoria.ui.view.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.example.savoria.R
import com.example.savoria.ui.theme.inter


@Composable
fun SearchView() {
    Column {
        var search by rememberSaveable { mutableStateOf("") }
        Row {
            Searchview_searchbar(
                value = search,
                onValueChanged = { search = it; },
                text = "search",
                keyboardOption = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 5.dp)
                    .background(Color.White, shape = CircleShape)
            )
        }
        Text(
            text = "Most Liked",
            fontSize = 24.sp,
            fontFamily = inter,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 5.dp)
        )
        LazyRow(
            modifier = Modifier.padding(horizontal = 20.dp)
        ){
            items(3){
                ContentCard()
            }
        }
        Text(
            text = "categories",
            fontSize = 24.sp,
            fontFamily = inter,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 5.dp)
        )
        LazyRow(
            modifier = Modifier.padding(horizontal = 20.dp)
        ){
            items(10){
                Categories_search()
            }
        }
        Text(
            text = "ingredients",
            fontSize = 24.sp,
            fontFamily = inter,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 5.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.padding(horizontal = 20.dp)
        ){
            items(10){
                Ingredients_search()
            }
        }
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
    TextField(
        shape = CircleShape,
        value = value,
        onValueChange = onValueChanged,
        placeholder = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = "search_icon"
                )
                Text(
                    text = text,
                    fontFamily = inter
                )
            }
        },
        keyboardOptions = keyboardOption,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFC6E4C9),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black
        ),
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun Categories_search() {
        val profileid = R.drawable.burger1
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
                Box (
                    contentAlignment =  Alignment.Center,
                    modifier = Modifier
                        .weight(2f)
                ){
                    Image(painter = painterResource(id = profileid),
                        contentDescription = "pic",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .padding(5.dp, 3.dp)
                            .clip(CircleShape)
                    )
                }

                Text(
                    text ="categoryname",
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
            Box (
                contentAlignment =  Alignment.Center,
                modifier = Modifier
                    .weight(2f)
            ){
                Image(painter = painterResource(id = profileid),
                    contentDescription = "pic",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(5.dp, 3.dp)
                        .clip(CircleShape)
                )
            }

            Text(
                text ="ingredientname",
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
fun ContentCard() {
    val imageData = painterResource(id = R.drawable.burger2)
    val title = "Burger"
    Card(
        modifier = Modifier
            .width(230.dp)
            .height(165.dp)
            .padding(10.dp),
        shape = RectangleShape,
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = imageData,
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = inter,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White.copy(alpha = 0.4f))
                )

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchpreView(){
    SearchView()
}
