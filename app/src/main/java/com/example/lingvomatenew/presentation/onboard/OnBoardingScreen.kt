package com.example.lingvomatenew.presentation.onboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lingvomatenew.R
import com.example.lingvomatenew.presentation.auth.onboard.ButtonUi
import com.example.lingvomatenew.presentation.auth.onboard.Indicator
import com.example.lingvomatenew.presentation.auth.onboard.OnBoardingGraphUi
import com.example.lingvomatenew.presentation.auth.onboard.OnBoardingModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(onFinished: () -> Unit) {

    val pages = listOf(
        OnBoardingModel.FirstPage,
        OnBoardingModel.SecondPage,
        OnBoardingModel.ThirdPage
    )

     val pagerState = rememberPagerState(initialPage = 0) {
        pages.size
     }

    val buttonState = remember {
        derivedStateOf{
            when(pagerState.currentPage){
                0 -> listOf("", "Next")
                1 -> listOf("Back", "Next")
                2 -> listOf("Back", "Start")
                else -> listOf("", "")
            }
        }
    }

    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = colorResource(R.color.specialblack),
        bottomBar ={ Row (modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){

            Box (modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart) {
                if (buttonState.value[0].isNotEmpty())
                {
                    ButtonUi(text = buttonState.value[0],
                        textColor = Color.Gray, backgroundColor = Color.Transparent
                    ) {

                        scope.launch {
                            if (pagerState.currentPage > 0) {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    }
                }
            }

            Box (modifier = Modifier.weight(1f), contentAlignment = Alignment.Center){
                Indicator(
                    pageSize = pages.size,
                    currentPage = pagerState.currentPage
                )
            }

            Box(modifier =  Modifier.weight(1f), contentAlignment = Alignment.CenterEnd){
                ButtonUi(text = buttonState.value[1],
                    backgroundColor = colorResource(R.color.blue),
                    textColor = Color.White
                ) {
                        if (pagerState.currentPage < pages.size - 1) {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }else {
                            onFinished()
                        }

                }
            }

        }},
        content = { Column(modifier = Modifier.padding(it)){
            HorizontalPager(state = pagerState) {index ->
                OnBoardingGraphUi(onBoardingModel = pages[index])
            }
        }},
    )

}


@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    OnBoardingScreen(onFinished = {})
}