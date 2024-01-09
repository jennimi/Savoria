package com.example.savoria.ui.view.home

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardBackspace
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.savoria.R
import com.example.savoria.model.CommentAndUser
import com.example.savoria.ui.theme.inter
import retrofit2.Response


@Composable
fun CommentsView(
    allCommentsResponse: Response<List<CommentAndUser>>,
    navController: NavController
) {

    val allComments: List<CommentAndUser> = allCommentsResponse.body()!!

    LazyColumn {
        item {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector =Icons.Outlined.KeyboardBackspace,
                    contentDescription = "back",
                    tint = Color(0xFF079f59),
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
                        }
                )
                Text(
                    text = "Comments",
                    fontFamily = inter,
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 15.dp)
                        .fillMaxWidth()
                )
            }
        }
        items(allComments.size) {
            val comment: CommentAndUser = allComments[it]
            Comment(comment)
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun CommentsPreview() {
//    CommentsView()
//}

@Composable
fun Comment(
    comment: CommentAndUser
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(45.dp)
                .clip(shape = CircleShape)
                .background(Color.LightGray)
        ) {
            LoadImageCustom(
                url = comment.user_image,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.None
            )
        }
        Column(
            modifier = Modifier
                .weight(3f)
                .padding(start = 16.dp, end = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Text(
                        text = comment.username,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        modifier = Modifier,
                        fontFamily = inter,
                    )
//                    Text(
//                        text = "2004-06-01",
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 15.sp,
//                        modifier = Modifier,
//                        fontFamily = inter,
//                    )
                }
            }
            Text(
                text = comment.comment,
                fontSize = 12.sp,
                maxLines = 2,
                modifier = Modifier
                    .padding(top = 3.dp),
                fontFamily = inter,
            )
        }
    }
    Spacer(modifier = Modifier.height(4.dp))
}
