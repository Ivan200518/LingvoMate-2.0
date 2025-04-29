package com.example.lingvomatenew.presentation.auth.onboard

import androidx.annotation.DrawableRes
import com.example.lingvomatenew.R

sealed class OnBoardingModel(
    @DrawableRes val image: Int,
    val title : String,
    val description : String
){
    data object FirstPage : OnBoardingModel(
        image = R.drawable.welcome2,
        title = "Learn a new language",
        description = "Learn a new language with Lingvomate, the app that makes language learning fun and easy."
    )

    data object SecondPage : OnBoardingModel(
        image = R.drawable.welcomescreen3,
        title = "Practice speaking",
        description = "Practice speaking with native speakers and improve your pronunciation."
    )

    data object ThirdPage : OnBoardingModel(
        image = R.drawable.welcome4,
        title = "Track your progress",
        description = "Track your progress and see how far you've come."
    )
}