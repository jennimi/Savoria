package com.example.savoria.ui

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.savoria.R
import com.example.savoria.ui.repository.MyDBRepositories

import com.example.savoria.ui.view.boarding.AppIntroView
import com.example.savoria.ui.view.boarding.LoginView
import com.example.savoria.ui.view.boarding.RegisterView


enum class Screen() {
    HomeView,
    SearchView,
    CreateRecipeView,
    BMICalcView,
    ProfileView,
    AppIntroView,
    LoginView,
    RegisterView,
}

sealed class BottomNavItem(var title: String, var icon: Int, var route: String) {

    object Home : BottomNavItem("Home", R.drawable.home, Screen.HomeView.name)
    object Search : BottomNavItem("Search", R.drawable.search, Screen.SearchView.name)
    object Create : BottomNavItem("Create", R.drawable.create_order, Screen.CreateRecipeView.name)
    object BMICalc : BottomNavItem("BMICalc", R.drawable.calculator, Screen.BMICalcView.name)
    object Profile : BottomNavItem("Profile", R.drawable.user, Screen.ProfileView.name)

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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SavoriaRoute(
    myDBRepositories: MyDBRepositories
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val navController = rememberNavController()

    var canNavigateBack by remember { mutableStateOf(false) }

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
            startDestination = Screen.AppIntroView.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            // boarding route
            composable(
                Screen.AppIntroView.name,
            ) {
                canNavigateBack = false
                AppIntroView (
                    { navController.navigate(Screen.LoginView.name) },
                    { navController.navigate(Screen.RegisterView.name) }
                )
            }

            composable(
                Screen.LoginView.name,
            ) {
                canNavigateBack = false
                LoginView(
                    { navController.navigate(Screen.HomeView.name) },
                    myDBRepositories,
                )
            }

            composable(
                Screen.RegisterView.name,
            ) {
                canNavigateBack = false
                RegisterView { navController.navigate(Screen.HomeView.name) }
            }
        }
    }
}
