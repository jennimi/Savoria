package com.example.savoria.ui.view.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.savoria.R
import com.example.savoria.ui.theme.inter


@Composable
fun FollowersView() {
    LazyColumn {
        item {
            Text(
                text = "Followers",
                fontFamily = inter,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp)
            )
        }
        item{
            var search by rememberSaveable { mutableStateOf("") }
            Row {
                Searchbarfollower(
                    value = search,
                    onValueChanged = { search = it; },
                    text = "search",
                    keyboardOption = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier
                        .weight(8f)
                        .padding(horizontal = 20.dp)
                        .background(Color.White, shape = CircleShape)
                )
            }
        }
        items(10) {
            follower()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FollowersPreview() {
    FollowersView()
}

@Composable
fun follower() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 3.dp)
            .clickable {

            }
    ) {
        Icon(
            imageVector = Icons.Rounded.Person,
            contentDescription = "profile",
            modifier = Modifier
                .clip(shape = CircleShape)
                .size(70.dp)
                .background(Color.LightGray)
                .align(Alignment.CenterVertically)
        )
        Column(
            modifier = Modifier
                .weight(3f)
                .padding(start = 8.dp, end = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "name",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        modifier = Modifier,
                        fontFamily = inter
                    )
                    Text(
                        text = "@name",
                        fontWeight = FontWeight.Light,
                        fontSize = 15.sp,
                        fontFamily = inter
                    )
                }
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier
                        .padding(top = 3.dp)
                ) {
                    Text(
                        text = "Following",
                        fontFamily = inter
                    )
                }
            }
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eget risus ipsum. Nulla ultricies sed lacus quis pulvinar. Nullam quis hendrerit odio. Curabitur vitae lectus dignissim nisl venenatis lobortis.",
                fontSize = 15.sp,
                maxLines = 2,
                modifier = Modifier
                    .padding(top = 3.dp),
                fontFamily = inter
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Searchbarfollower(
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
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFC6E4C9),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black
        ),
        modifier = modifier.fillMaxWidth()
    )
}