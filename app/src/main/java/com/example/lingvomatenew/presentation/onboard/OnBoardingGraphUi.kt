package com.example.lingvomatenew.presentation.auth.onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun OnBoardingGraphUi(onBoardingModel: OnBoardingModel) {
    Column(modifier = Modifier.fillMaxWidth()) {

        Image(
            painter = painterResource(id = onBoardingModel.image),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().size(width = 390.dp, height = 450.dp),
            alignment = Alignment.TopStart
        )

        Spacer(modifier = Modifier.size(80.dp))

        Text(
            text = onBoardingModel.title,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = onBoardingModel.description,
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp),
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.size(50.dp))



    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingGraphUiPreview1() {
    OnBoardingGraphUi(OnBoardingModel.FirstPage)
}
@Preview(showBackground = true)
@Composable
fun OnBoardingGraphUiPreview2() {
    OnBoardingGraphUi(OnBoardingModel.SecondPage)
}
@Preview(showBackground = true)
@Composable
fun OnBoardingGraphUiPreview3() {
    OnBoardingGraphUi(OnBoardingModel.ThirdPage)
}