package com.example.onceshare.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
//import com.example.onceshare.domain.usecases.UserProfileScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("add_appliance") { AddApplianceScreen(navController) }
       // composable("appliance_list") { ApplianceListScreen(navController) }
        composable(
            "appliance_detail/{applianceId}",
            arguments = listOf(navArgument("applianceId") { type = NavType.StringType })
        ) { //backStackEntry ->
            //ApplianceDetailScreen(navController, backStackEntry.arguments?.getString("applianceId") ?: "")
        }
        composable(
            "booking/{applianceId}",
            arguments = listOf(navArgument("applianceId") { type = NavType.StringType })
        ) { backStackEntry ->
            BookingScreen(navController, backStackEntry.arguments?.getString("applianceId") ?: "")
        }
        composable("user_profile") { UserProfileScreen(navController) }
        composable(
            "chat/{channelId}",
            arguments = listOf(navArgument("channelId") { type = NavType.StringType })
        ) { backStackEntry ->
           // ChatScreen(navController, backStackEntry.arguments?.getString("channelId") ?: "")
        }
    }
}


