package com.example.onceshare.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.onceshare.R
import io.getstream.chat.android.compose.ui.channels.ChannelsScreen
import io.getstream.chat.android.compose.ui.messages.MessagesScreen
import io.getstream.chat.android.compose.viewmodel.messages.MessagesViewModelFactory

//import com.example.onceshare.domain.usecases.UserProfileScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("add_appliance") { AddApplianceScreen(navController) }
        composable("appliance_list") { ApplianceListScreen(navController) }
        composable("booking") { BookingScreen(navController, applianceId="") }
        composable("user") { UserProfileScreen(navController) }
        composable(
            "appliance_detail/{applianceId}",
            arguments = listOf(navArgument("applianceId") { type = NavType.StringType })
        ) { backStackEntry ->
            ApplianceDetailScreen(navController, backStackEntry.arguments?.getString("applianceId") ?: "")
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
        composable("chat") {
            ChannelsScreen(
                title = stringResource(id = R.string.app_name),
                onChannelClick = { channel ->
                    navController.navigate("messages/${channel.cid}")
                },
                onBackPressed = { navController.popBackStack() }
            )
        }
        composable(
            "messages/{channelId}",
            arguments = listOf(navArgument("channelId") { type = NavType.StringType })
        ) { backStackEntry ->
            MessagesScreen(
                viewModelFactory = MessagesViewModelFactory(
                    context = LocalContext.current,
                    channelId = backStackEntry.arguments?.getString("channelId") ?: "",
                    messageLimit = 30
                ),
                onBackPressed = { navController.popBackStack() }
            )
        }
    }
}
