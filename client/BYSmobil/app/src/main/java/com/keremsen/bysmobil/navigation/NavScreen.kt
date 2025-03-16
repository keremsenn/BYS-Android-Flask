package com.keremsen.bysmobil.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.keremsen.bysmobil.view.LoginPage
import com.keremsen.bysmobil.view.MainPage
import com.keremsen.bysmobil.view.TranscriptPage


@Composable
fun NavScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "loginPage") {
        composable(
            "main/{is_student}/{studentId}/{advisorId}",
            arguments = listOf(
                navArgument("is_student") { type = NavType.StringType },
                navArgument("studentId"){ type = NavType.IntType },
                navArgument("advisorId"){type=NavType.IntType}
            )
        ) { backStackEntry ->
            val isStudent = backStackEntry.arguments?.getString("is_student").toBoolean()
            val studentId = backStackEntry.arguments?.getInt("studentId")!!.toInt()
            val advisorId = backStackEntry.arguments?.getInt("advisorId")!!.toInt()
            MainPage(navController, isStudent, studentId,advisorId)
        }
        composable("loginPage") {
            LoginPage(navController)
        }
        composable("transcript/{studentId}",
            arguments = listOf(
                navArgument("studentId"){ type = NavType.IntType }
            )
        ) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getInt("studentId")!!.toInt()
            TranscriptPage(navController,studentId )
        }
    }

}