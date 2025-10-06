package com.example.josmeyly_duarte_p1_p2.Presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.josmeyly_duarte_p1_p2.Presentation.list.ListHuacalesScreen
import com.example.josmeyly_duarte_p1_p2.Presentation.edit.EditHuacalesScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            ListHuacalesScreen(
                onNavigateToEdit = { id -> navController.navigate("edit/$id") },
                onNavigateToCreate = { navController.navigate("edit/0") }
            )
        }

        composable(
            route = "edit/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            EditHuacalesScreen()
        }
    }
}