package com.example.savoria.ui.view.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Comment
import androidx.compose.material.icons.outlined.KeyboardBackspace
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.savoria.R
import com.example.savoria.model.Category
import com.example.savoria.model.RecipeResponse
import com.example.savoria.ui.view.home.RecipeContent
import com.example.savoria.ui.view.home.RecipesContainer
import com.example.savoria.viewmodel.HomeViewModel
import retrofit2.Response

@Composable
fun CategoryView(
    categoryName: String,
    allRecipesResponse: Response<List<RecipeResponse>>,
    homeViewModel: HomeViewModel,
    navController: NavController,
){
//    percobaan

    val allRecipes: List<RecipeResponse>? = allRecipesResponse.body()
    Column (
        modifier = Modifier
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Row () {
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
                text = categoryName,
                color = Color.Black,
                fontFamily = SavFont,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(bottom = 28.dp),
                )
        }
        RecipesContainer(allRecipes = allRecipes, homeViewModel = homeViewModel, navController = navController)

//        LazyColumn(
//            verticalArrangement = Arrangement.spacedBy(28.dp)
//        ){
//            items(8){ index ->
//                OneCardCategoryView()
//            }
//        }
    }
}


//ini untuk satu card nya
@Composable
fun OneCardCategoryView(){
//    pakai box untuk buat gambar dan text nya
    Box (
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            //        ini untuk shadow di card nya
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            shape = RoundedCornerShape(20.dp),
            //        ini untuk ukurannya
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
//                .size(width = 312.dp, height = 203.dp)
                .width(312.dp)
                .height(203.dp),
            //        ini untuk atur warnanya
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF179C5B)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
            ){
                Image(
                    painter = painterResource(R.drawable._e25dff88d4fb38ce1083d3493efcc96),
                    contentDescription = "ImageAwal",
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(312.dp)
                        .height(135.dp),
                    contentScale = ContentScale.FillWidth
                )
//                Text Satu
                Text(
                    text = "Rich Oil Drizzle Medley",
                    color = Color.White,
                    fontFamily = SavFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 12.dp, end = 5.dp, top = 12.dp)
                )
//                Text Dua
                Text(
                    text = "Colorful veggies in a rich, aromatic oil drizzle",
                    color = Color.White,
                    fontFamily = SavFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .padding(start = 12.dp, top = 5.dp)
                )
            }
        }
    }
}

//ini untuk simpan semua font inter nya
val SavFont = FontFamily(
    Font(R.font.inter_extralight, FontWeight.ExtraLight),
    Font(R.font.inter_thin, FontWeight.Thin),
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_semibold, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold),
    Font(R.font.inter_extrabold, FontWeight.ExtraBold),
    Font(R.font.inter_black, FontWeight.Black),
)

//ini untuk simpan font Lobster nya
val LobsFont = FontFamily(

    Font(R.font.lobster_regular, FontWeight.Normal)
)

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun CategoryViewPreview(){
//
//    CategoryView()
//}

