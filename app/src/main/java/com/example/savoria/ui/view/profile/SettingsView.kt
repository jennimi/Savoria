package com.example.savoria.ui.view.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardBackspace
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.savoria.R
import com.example.savoria.data.DataStoreManager
import com.example.savoria.ui.view.search.SavFont
import com.example.savoria.viewmodel.AuthViewModel

@Composable
fun SettingView(
    authViewModel: AuthViewModel,
    navController: NavController,
    dataStore: DataStoreManager,
){
    Column (
        modifier = Modifier.padding(24.dp)
    ){
        Row {
            Icon(
                imageVector = Icons.Outlined.KeyboardBackspace,
                contentDescription = "back",
                tint = Color(0xFF079f59),
                modifier = Modifier
                    .clickable {
                        navController.popBackStack()
                    }
            )

            Text(
                text = "Settings",
                color = Color.Black,
                fontFamily = SavFont,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
            )
        }

        Account()
        tombolOut( authViewModel, navController, dataStore)
    }
}

@Composable
fun Account(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 23.dp)
    ) {
        Text(
            text = "Account",
            color = Color.Black,
            fontFamily = SavFont,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(bottom = 17.dp)

        )
        Row{
            Image(
                painter = painterResource(id = R.drawable.baseline_person_24),
                contentDescription = "Person 1",
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
            )
            Text(
                text = "Change Profile",
                color = Color.Black,
                fontFamily = SavFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 17.sp,
                modifier = Modifier
                    .padding(start = 12.dp)
            )
        }
        Text(
            text = "Security",
            color = Color.Black,
            fontFamily = SavFont,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(top = 23.dp, bottom = 17.dp)
        )
        Row {
            Image(painter = painterResource(id = R.drawable.baseline_lock_24),
                contentDescription = "Lock Password",
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
            )
            Text(
                text = "Change Password",
                color = Color.Black,
                fontFamily = SavFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 17.sp,
                modifier = Modifier
                    .padding(start = 12.dp)
            )
        }
    }
}

@Composable
fun tombolOut(
    authViewModel: AuthViewModel,
    navController: NavController,
    dataStore: DataStoreManager
){
    OutlinedButton(
        onClick = { authViewModel.LogOut(navController, dataStore) },
        modifier = Modifier
//            .padding(top = 458.dp),
            .padding(top = 200.dp),
        border = BorderStroke(2.dp, Color(0xFF6FC18F))
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Log Out",
                    color = Color.Black,
                    fontFamily = SavFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(end = 5.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.baseline_logout_24),
                    contentDescription = "Logout",
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                )
            }
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun SettingViewPreview(){
////Account()
//    SettingView()
//}