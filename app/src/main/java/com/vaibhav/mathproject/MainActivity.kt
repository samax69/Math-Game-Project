package com.vaibhav.mathproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vaibhav.mathproject.ui.theme.MathProjectTheme
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MathProjectTheme {
                MyNavigation()
            }
        }
    }
}


@Composable
fun MyNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "FirstPage"
    ) {
        composable(route = "FirstPage") {
            FirstPage(navController = navController)
        }

        composable(
            route = "SecondPage/{category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val selectedCategory =
                backStackEntry.arguments?.getString("category")

            selectedCategory?.let { category ->
                SecondPage(navController, category = category)
            }
        }

        composable(
            route = "ResultPage/{score}",
            arguments = listOf(
                navArgument("score") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val userScore =
                backStackEntry.arguments?.getInt("score")

            userScore?.let { score ->
                ResultPage(navController, score = score)
            }
        }
    }
}