package com.example.materialcompose.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.materialcompose.screens.LoginScreen
import com.example.materialcompose.screens.MainScreen
import com.example.materialcompose.ui.theme.*
import com.example.materialcompose.viewModel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {

            MaterialComposeTheme {
                val navController = rememberNavController()
                val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
                val topBarState = rememberSaveable { (mutableStateOf(true)) }
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                when (navBackStackEntry?.destination?.route) {
                    "main" -> {
                        bottomBarState.value = true
                        topBarState.value = true
                    }

                    "chat" -> {
                        bottomBarState.value = true
                        topBarState.value = true
                    }

                    "settings" -> {
                        bottomBarState.value = true
                        topBarState.value = true
                    }

                    "new_screen" -> {
                        bottomBarState.value = false
                        topBarState.value = false
                    }
                }
                Scaffold(topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "Compose App",
                                fontFamily = poppins,
                                color = MaterialTheme.colorScheme.onError
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary),
                        modifier = Modifier.shadow(elevation = 0.dp)
                    )
                }) {
                    Navigation(navController)
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            LoginScreen(navController)
        }
        composable(
            "new_screen/{email}", arguments = listOf(navArgument("email") {
                type = NavType.StringType
            })
        ) {
            MainScreen(it.arguments?.getString("email").toString(),hiltViewModel())
        }
    }

}
