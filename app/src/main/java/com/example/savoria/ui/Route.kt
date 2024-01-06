package com.example.savoria.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.savoria.R
import com.example.savoria.repository.SavoriaContainer
import com.example.savoria.data.DataStoreManager
import com.example.savoria.ui.view.bmicalc.BMICalcView

import com.example.savoria.ui.view.boarding.AppIntroView
import com.example.savoria.ui.view.boarding.LoginView
import com.example.savoria.ui.view.boarding.RegisterView
import com.example.savoria.ui.view.create.CreateRecipeView
import com.example.savoria.ui.view.home.ViewHome
import com.example.savoria.ui.view.profile.ProfileView
import com.example.savoria.ui.view.profile.SettingView
import com.example.savoria.ui.view.search.SearchView

import com.example.savoria.viewmodel.UserViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


enum class Screen() {
    Home,
    Search,
    CreateRecipe,
    BMICalc,
    Profile,
    AppIntro,
    Login,
    Register,
    Settings,
}

sealed class BottomNavItem(var title: String, var icon: Int, var route: String) {

    object Home : BottomNavItem("Home", R.drawable.home, Screen.Home.name)
    object Search : BottomNavItem("Search", R.drawable.search, Screen.Search.name)
    object Create : BottomNavItem("Create", R.drawable.create_order, Screen.CreateRecipe.name)
    object BMICalc : BottomNavItem("BMICalc", R.drawable.calculator, Screen.BMICalc.name)
    object Profile : BottomNavItem("Profile", R.drawable.user, Screen.Profile.name)

}

@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Create,
        BottomNavItem.BMICalc,
        BottomNavItem.Profile
    )

    NavigationBar(
        // https://stackoverflow.com/questions/70942583/what-is-color-of-navigationbar-in-jetpack-compose-in-material-color-scheme
//        containerColor = CalmGreen
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
//                        modifier = if (item.icon == R.drawable.baseline_add_circle_24) {
//                            Modifier.size(48.dp)
//                        } else {
//                            Modifier.size(28.dp)
//                        }
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                // Customize the colors
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.White,
                    selectedTextColor = Color.Transparent,
//                    indicatorColor = CalmGreen // ini warna efek clickednya!
                )
            )
        }
    }
}

@DelicateCoroutinesApi
@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavoriaRoute(
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val navController = rememberNavController()
    val dataStore = DataStoreManager(LocalContext.current)

    var canNavigateBack by remember { mutableStateOf(false) }

    GlobalScope.launch {
        dataStore.getToken.collect{ token ->
            if (token != null){
                SavoriaContainer.ACCESS_TOKEN = token
            }
        }
    }

    Scaffold (
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        bottomBar = {
            if (canNavigateBack) {
                BottomNavBar(navController)
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.AppIntro.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            // boarding route
            composable(
                Screen.AppIntro.name,
            ) {
                canNavigateBack = false
                AppIntroView (
                    { navController.navigate(Screen.Login.name) },
                    { navController.navigate(Screen.Register.name) }
                )
            }

            composable(Screen.Login.name){
                if(SavoriaContainer.ACCESS_TOKEN.isEmpty()){
                    val userViewModel: UserViewModel = viewModel()
                    LoginView(
                        userViewModel = userViewModel,
                        navController = navController,
                        dataStore = dataStore
                    )
                }else{
                    navController.navigate(Screen.Home.name){
                        popUpTo(Screen.Login.name) { inclusive = true }
                    }
                }
            }

            composable(
                Screen.Register.name
            ) {
                canNavigateBack = false
                val userViewModel: UserViewModel = viewModel()
                RegisterView(
                    userViewModel = userViewModel,
                    dataStore = dataStore,
                    navController = navController
                )
            }
            // end boarding route


            composable(
                Screen.Home.name,
            ) {
                canNavigateBack = true
                ViewHome()
            }

            composable(
                Screen.Search.name,
            ) {
                canNavigateBack = true
                SearchView()
            }

            composable(
                Screen.CreateRecipe.name,
            ) {
                canNavigateBack = true
                CreateRecipeView()
            }

            composable(
                Screen.BMICalc.name
            ) {
                canNavigateBack = true
                BMICalcView()
            }

            composable(
                Screen.Profile.name
            ) {
                canNavigateBack = true
                ProfileView { navController.navigate(Screen.Settings.name) }
            }

            composable(
                Screen.Settings.name
            ) {
                // no nav bar
                canNavigateBack = true
                val userViewModel: UserViewModel = viewModel()
                SettingView(
                    userViewModel = userViewModel,
                    navController = navController,
                    dataStore = dataStore,
                )
            }

        }
    }
}
