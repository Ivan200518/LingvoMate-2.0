package com.example.lingvomatenew.presentation.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lingvomatenew.presentation.auth.signin.SignInScreen
import com.example.lingvomatenew.presentation.auth.signup.SignUpScreen
import com.example.lingvomatenew.presentation.home.HomeScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth


@Composable
fun MainApp() {

    val currentUser = FirebaseAuth.getInstance().currentUser
    val navController = rememberNavController()
    val start = if (currentUser != null) "home" else "login"
    NavHost(
        navController = navController, startDestination = start
    ) {

        composable("home" ) {
            HomeScreen()
        }

        composable("login") {
            SignInScreen()
        }
        composable("signup") {
            SignUpScreen()
        }
    }
}