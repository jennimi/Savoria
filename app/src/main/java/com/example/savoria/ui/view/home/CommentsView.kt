package com.example.savoria.ui.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.savoria.R
import com.example.savoria.ui.theme.inter


@Composable
fun CommentsView() {
    LazyColumn {
        item {
            Text(
                text = "Comments",
                fontFamily = inter,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp)
                    .fillMaxWidth()
            )
        }
        items(10) {
            Comment()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CommentsPreview() {
    CommentsView()
}

@Composable
fun Comment() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 3.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(shape = CircleShape)
                .background(Color.LightGray)
        ) {
            Image(
                painter = painterResource(id = R.drawable.round_person_24),
                contentDescription = "profile",
                modifier = Modifier.fillMaxSize()
            )
        }
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
                        fontFamily = inter,
                    )
                }
            }
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur eget risus ipsum. Nulla ultricies sed lacus quis pulvinar. Nullam quis hendrerit odio. Curabitur vitae lectus dignissim nisl venenatis lobortis.",
                fontSize = 15.sp,
                maxLines = 2,
                modifier = Modifier
                    .padding(top = 3.dp),
                fontFamily = inter,
            )
        }
    }
}
