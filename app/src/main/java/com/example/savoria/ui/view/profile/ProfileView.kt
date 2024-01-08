package com.example.savoria.ui.view.profile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.savoria.ui.view.search.SavoriaFont
import com.example.savoria.R
import com.example.savoria.model.User
import com.example.savoria.model.UserResponse
import com.example.savoria.viewmodel.ProfileUIState
import com.example.savoria.viewmodel.ProfileViewModel
import retrofit2.Response

@Composable
fun ProfileView(
    profileViewModel: ProfileViewModel,
    toSettings: () -> Unit,
){
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            AllProfile( profileViewModel, toSettings )
            iconProfile()
            garisNav()
            ContentPost()
        }
    }
}

@Composable
fun AllProfile(
    profileViewModel: ProfileViewModel,
    toSettings: () -> Unit,
){

    val profileUIState: ProfileUIState = profileViewModel.profileUIState
    var currentUserDetails: Response<UserResponse>? = null
    var currentUser: Response<User>? = null

    when (profileUIState) {
        is ProfileUIState.Success -> {
            currentUserDetails = profileUIState.userInSessionDetails
            currentUser = profileUIState.userInSession
        }
        is ProfileUIState.Error -> {
            Log.e("AllProfile", "Error fetching data")
        }
        ProfileUIState.Loading -> {
            Log.d("AllProfile", "Loading...")
        }

        else -> {}
    }

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
            Button(
                onClick = toSettings
            ) {
                Text(text="Settings")
            }
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
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF179C5B)),
                modifier = Modifier
                    .width(67.67.dp)
                    .height(18.dp),
                contentPadding = PaddingValues(0.dp),
            ) {
                Text(
                    text = "Edit Profile",
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
                color = Color(0xFF024424)
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
                    .width(120.dp)
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