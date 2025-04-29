package com.example.lingvomatenew.presentation.auth.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.lingvomatenew.R
import com.example.lingvomatenew.presentation.auth.onboard.OnBoardingScreen
import com.example.lingvomatenew.ui.theme.LingvoMateNewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            LingvoMateNewTheme {
                ShowOnboardingScreen()
            }
        }
    }
}


@Composable
private fun ShowOnboardingScreen() {


    val context = LocalContext.current

    Box(modifier = Modifier.background(color = colorResource(R.color.specialblack))){
        OnBoardingScreen {
            Toast.makeText(context,"Onboarding Completed", Toast.LENGTH_SHORT).show()
        }

    }
}