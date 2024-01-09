package com.example.savoria.ui

import android.annotation.SuppressLint
import android.util.Log
import android.widget.SearchView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
import com.example.savoria.model.RecipeResponse
import com.example.savoria.ui.view.bmicalc.BMICalcView

import com.example.savoria.ui.view.boarding.AppIntroView
import com.example.savoria.ui.view.boarding.LoginView
import com.example.savoria.ui.view.boarding.RegisterView
import com.example.savoria.ui.view.create.CreateRecipeView
import com.example.savoria.ui.view.home.CommentsView
import com.example.savoria.ui.view.home.HomeView
import com.example.savoria.ui.view.home.RecipeView
import com.example.savoria.ui.view.profile.ProfileView
import com.example.savoria.ui.view.profile.SettingView
import com.example.savoria.ui.view.search.CategoryView
import com.example.savoria.ui.view.search.SearchResultView
import com.example.savoria.ui.view.search.SearchView

import com.example.savoria.viewmodel.AuthViewModel
import com.example.savoria.viewmodel.CategoryUIState
import com.example.savoria.viewmodel.CategoryViewModel
import com.example.savoria.viewmodel.CommentsUIState
import com.example.savoria.viewmodel.CommentsViewModel
import com.example.savoria.viewmodel.RecipeViewModel
import com.example.savoria.viewmodel.HomeViewModel
import com.example.savoria.viewmodel.ProfileViewModel
import com.example.savoria.viewmodel.RecipeDetailUIState
import com.example.savoria.viewmodel.RecipeDetailViewModel
import com.example.savoria.viewmodel.SearchResultsUiState
import com.example.savoria.viewmodel.SearchResultsViewModel
import com.example.savoria.viewmodel.SearchViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response


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
    RecipeView,
    ResultsView,
    CommentsView,
    CategoryView,
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
        containerColor = Color.Transparent
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        modifier = Modifier
                            .size(42.dp)
                            .padding(horizontal = 4.dp)
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    navController.navigate(item.route) {
//                        popUpTo(navController.graph.findStartDestination().id) {
//                            saveState = true
//                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF179B5B),
                    unselectedIconColor = Color.Black,
                    selectedTextColor = Color.Transparent,
                    indicatorColor = Color(0xFFFFFFFF),
                ),
                modifier = Modifier
                    .background(Color.Transparent)
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
        dataStore.getUserid.collect{ userid ->
            if (userid != null) {
                SavoriaContainer.USER_ID = userid
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
                    navController
                )
            }

            composable(Screen.Login.name){
                if(SavoriaContainer.ACCESS_TOKEN.isEmpty()){
                    val authViewModel: AuthViewModel = viewModel()
                    LoginView(
                        authViewModel = authViewModel,
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
                val authViewModel: AuthViewModel = viewModel()
                RegisterView(
                    authViewModel = authViewModel,
                    dataStore = dataStore,
                    navController = navController,
                    context = LocalContext.current
                )
            }
            // end boarding route


            composable(
                Screen.Home.name,
            ) {
                val homeViewModel: HomeViewModel = viewModel()
                canNavigateBack = true
                HomeView(
                    homeViewModel = homeViewModel,
                    navController = navController
                )
            }

            composable(
                Screen.Search.name,
            ) {
                val searchViewModel: SearchViewModel = viewModel()
                canNavigateBack = true
                SearchView(searchViewModel, navController)
            }

            composable(
                Screen.CreateRecipe.name,
            ) {
                val recipeViewModel: RecipeViewModel = viewModel()
                canNavigateBack = true
                CreateRecipeView(
                    recipeViewModel,
                    context = LocalContext.current,
                    navController = navController
                )
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
                val profileViewModel: ProfileViewModel = viewModel()
                val homeViewModel: HomeViewModel = viewModel()

                canNavigateBack = true
                ProfileView (
                    profileViewModel = profileViewModel,
                    { navController.navigate(Screen.Settings.name) },
                    homeViewModel = homeViewModel,
                    navController = navController
                )
            }

            composable(
                Screen.Settings.name
            ) {
                canNavigateBack = false
                val authViewModel: AuthViewModel = viewModel()
                SettingView(
                    authViewModel = authViewModel,
                    navController = navController,
                    dataStore = dataStore,
                )
            }

            composable(
                Screen.RecipeView.name+"/{id}"
            ) {
                canNavigateBack = false
                var recipeidString: String = ""
                val recipeDetailViewModel: RecipeDetailViewModel = viewModel()

                it.arguments?.let { it1 ->
                    recipeidString = it1.getString("id", "2")
                    val recipeid = recipeidString.toInt()

                    LaunchedEffect(key1 = true) {
                        recipeDetailViewModel.initializeRecipeId(recipeid)
                    }

                    val status = recipeDetailViewModel.recipeDetailUIState

                    when (status) {
                        is RecipeDetailUIState.Loading -> {
                        }
                        is RecipeDetailUIState.Success -> {
                            RecipeView(
                                navController = navController,
                                recipeResponse = status.recipe,
                                userResponse = status.user
                            )
                        }
                        is RecipeDetailUIState.Error ->{
                        }

                        else -> {
                        }
                    }
                }
            }

            composable(
                Screen.ResultsView.name+"/{search}"
            ) {
                canNavigateBack = true
                val searchResultsViewModel: SearchResultsViewModel = viewModel()
                val homeViewModel: HomeViewModel = viewModel()

                var search: String = ""

                it.arguments?.let { it2 ->
                    search = it2.getString("search", "burger")
                    
                    LaunchedEffect(key1 = true) {
                        searchResultsViewModel.getSearchResults(search)
                    }

                    val status = searchResultsViewModel.searchResultsUiState

                    when (status) {
                        is SearchResultsUiState.Loading -> {
                        }
                        is SearchResultsUiState.Success -> {
                            SearchResultView(
                                search = search,
                                resultsListResponse = status.searchResults,
                                homeViewModel = homeViewModel,
                                navController = navController
                            )
                        }
                        is SearchResultsUiState.Error ->{
                        }

                        else -> {
                        }
                    }
                }
            }

            composable(
                Screen.CommentsView.name+"/{id}"
            ) {
                canNavigateBack = false
                val commentsViewModel: CommentsViewModel = viewModel()
                var recipeidString: String = ""

                it.arguments?.let { it1 ->
                    recipeidString = it1.getString("id", "1")
                    val recipeid = recipeidString.toInt()

                    LaunchedEffect(key1 = true) {
                        commentsViewModel.initializeComments(recipeid)
                    }

                    val status = commentsViewModel.commentsUIState

                    when (status) {
                        is CommentsUIState.Loading -> {
                        }
                        is CommentsUIState.Success -> {
                            CommentsView (
                                allCommentsResponse = status.comments,
                                navController = navController
                            )
                        }
                        is CommentsUIState.Error ->{
                        }
                        else -> {
                        }
                    }
                }
            }

            composable(
                Screen.CategoryView.name+"/{id}"
            ) {
                canNavigateBack = false
                val categoryViewModel: CategoryViewModel = viewModel()
                val homeViewModel: HomeViewModel = viewModel()
                var recipeidString: String = ""

                it.arguments?.let { it1 ->
                    recipeidString = it1.getString("id", "1")
                    val recipeid = recipeidString.toInt()

                    LaunchedEffect(key1 = true) {
                        categoryViewModel.initializeCategory(recipeid)
                    }

                    val status = categoryViewModel.categoryUIState

                    when (status) {
                        is CategoryUIState.Loading -> { }
                        is CategoryUIState.Success -> {
                            CategoryView(
                                categoryName = status.categoryName,
                                allRecipesResponse = status.allRecipes,
                                homeViewModel = homeViewModel,
                                navController = navController
                            )
                        }
                        is CategoryUIState.Error -> { }
                        else -> { }
                    }
                }
            }


        }
    }
}
