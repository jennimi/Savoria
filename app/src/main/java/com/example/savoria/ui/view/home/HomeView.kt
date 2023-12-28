package com.example.savoria.ui.view.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.savoria.R
import com.example.savoria.ui.theme.inter
import com.example.savoria.ui.theme.lobster

@Composable
fun ViewHome() {
    Column {
        LazyColumn {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFF079f59),
                            shape = RoundedCornerShape(0.dp, 0.dp, 40.dp, 40.dp)
                        )
                        .padding(horizontal = 15.dp, vertical = 20.dp)
                        .clip(shape = RoundedCornerShape(0.dp, 0.dp, 40.dp, 40.dp))
                ) {
                    //App name
                    Text(
                        text = "Savoria",
                        fontFamily = inter,
                        fontSize = 30.sp,
                        color = Color.White,
                        modifier = Modifier
                            .align(CenterHorizontally)
                    )
                    //App name
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        //welcome text
                        Text(
                            text = "Hey there Louis!\nready to explore for \nsome recipe?",
                            fontFamily = lobster,
                            fontSize = 25.sp,
                            color = Color.White,
                            modifier = Modifier
                                .weight(6f)
                        )
                        //welcome text

                        //profile picture
                        Image(
                            painter = painterResource(id = R.drawable.round_person_24),
                            contentDescription = "profile_pic",
                            modifier = Modifier
                                .size(128.dp)
                                .padding(20.dp)
                                .weight(4f)
                        )
                        //profile picture


                    }
                    var search by rememberSaveable { mutableStateOf("") }
                    Row {
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
                                .weight(8f)
                                .padding(horizontal = 10.dp)
                                .background(Color.White, shape = CircleShape)
                        )
                        //searchbar

                        //filter
                        Image(
                            painter = painterResource(id = R.drawable.outline_filter_alt_24), // Replace with your filter icon
                            contentDescription = "Filter Icon",
                            modifier = Modifier
                                .size(50.dp)
                                .clickable { }
                                .padding(end = 6.dp)
                                .background(Color.White, shape = CircleShape)
                                .weight(2f)
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
                                        .padding(8.dp)
                                )
                            }
                        }
                    }

                    // calling Content for following and for you
                    when (selectedTabIndex) {
                        0 -> Following()
                        1 -> Foryou()
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
                horizontalArrangement = Arrangement.spacedBy(8.dp)
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
            cursorColor = Color.Black
        ),
        modifier = modifier.fillMaxWidth()
    )
}

//searchbar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Following() {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(10) {
                Contentcard()
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .height(1000.dp)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Foryou() {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(10) {
                Contentcard()
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .height(1000.dp)
    )
}

@Composable
fun Contentcard() {
    var isLiked by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(10.dp),
        shape = RectangleShape,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color.White)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                //image
                Image(
                    painter = painterResource(id = R.drawable.burger1),
                    contentDescription = "image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                )
                //image

                //Like Button
                FloatingActionButton(
                    onClick = {
                        isLiked = !isLiked
                    },
                    containerColor = Color(0xFF079f59),
                    modifier = Modifier
                        .width(50.dp)
                        .height(25.dp)
                        .align(alignment = BottomEnd)
                        .padding(end = 10.dp, bottom = 10.dp)
                ) {
                    Row {
                        Text(
                            text = "1",
                            color = Color.White,
                            fontFamily = inter
                        )
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Favorite",
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
                text = "Burger",
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontFamily = inter
            )
            //title

            //description
            Text(
                text = "Description",
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 15.sp,
                color = Color.Black,
                fontFamily = inter
            )
            //description
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHome() {
    ViewHome()
}
