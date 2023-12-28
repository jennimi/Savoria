package com.example.savoria.ui.view.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import com.example.savoria.R
import com.example.savoria.ui.theme.inter
import com.example.savoria.ui.view.home.Contentcard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchResultView() {

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 10.dp)
    ) {
        item {
            var name by rememberSaveable { mutableStateOf("pasta") }
            var search by rememberSaveable { mutableStateOf("") }
            Row {
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
                        .weight(8f)
                        .background(Color.White, shape = CircleShape)
                )
                //searchbar
            }
            Text(
                text = "Search result for '$name' ",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontFamily = inter
            )
            Text(
                text = "found 8 relevant recipes ",
                fontSize = 16.sp,
                fontFamily = inter
            )
            val contentList = List(10) {}

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                content = {
                    items(contentList.size) {
                        Contentcard()
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .height(1000.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchResultpreView() {
    SearchResultView()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Searchresult_Searchbar(
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
            containerColor = Color(0xFFC6E4C9),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black
        ),
        modifier = modifier.fillMaxWidth()
    )
}




