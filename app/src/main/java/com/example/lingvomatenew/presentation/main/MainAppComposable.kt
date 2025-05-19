package com.example.lingvomatenew.presentation.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lingvomatenew.presentation.auth.signin.SignInScreen
import com.example.lingvomatenew.presentation.auth.signin.SignUpScreen
import com.example.lingvomatenew.presentation.auth.signup.SignUpViewModel
import com.example.lingvomatenew.presentation.chat.ChatScreen
import com.example.lingvomatenew.presentation.home.HomeScreen
import com.example.lingvomatenew.presentation.onboard.OnBoardingScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth


@Composable
fun MainApp() {

    val currentUser = FirebaseAuth.getInstance().currentUser
    val navController = rememberNavController()
    val start = if(currentUser != null) RouteScreens.HOME else RouteScreens.ONBOARDING
    NavHost(
        navController = navController, startDestination = start
    ) {
        composable(RouteScreens.ONBOARDING) {
            OnBoardingScreen(navController)
        }
        composable(RouteScreens.LOGIN) {
            SignInScreen(navController)
        }
        composable(RouteScreens.SIGN_UP) {
            SignUpScreen(navController)
        }
        composable(RouteScreens.HOME) {
            HomeScreen(navController)
        }

        composable("chat/{channelId}", arguments = listOf(
            navArgument("channelId") {
                type = NavType.StringType
            }
        )) {
            val channelId = it.arguments?.getString("channelId") ?: ""
            ChatScreen(navController, channelId)
        }
    }

}

object RouteScreens {
    const val LOGIN = "login"
    const val SIGN_UP = "signup"
    const val HOME = "home"
    const val ONBOARDING = "onboarding"
}

