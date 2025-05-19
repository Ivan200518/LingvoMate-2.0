package com.example.lingvomatenew.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.lingvomatenew.R
import com.example.lingvomatenew.presentation.onboard.OnBoardingScreen
import com.example.lingvomatenew.ui.theme.LingvoMateNewTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            LingvoMateNewTheme {
                MainApp()
            }
        }
    }
}


@Composable
private fun ShowOnboardingScreen() {


    val context = LocalContext.current
    Box(modifier = Modifier.background(color = colorResource(R.color.specialblack))){
        OnBoardingScreen()
    }
}