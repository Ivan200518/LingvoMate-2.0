package com.example.lingvomatenew.presentation.auth.onboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lingvomatenew.R


@Composable
fun Indicator(
    pageSize : Int,
    currentPage : Int,
    selectedColor : Color= colorResource(R.color.blue),
    unselectedColor : Color = MaterialTheme.colorScheme.secondaryContainer
) {
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        repeat(pageSize){
            Spacer(modifier = Modifier.size(5.dp))
            Box(modifier = Modifier.height(16.dp)
                .width(16.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = if(it == currentPage) selectedColor else unselectedColor))
            Spacer(modifier = Modifier.size(5.dp))
        }

    }

}

@Preview
@Composable
fun IndicatorPreview1(){
    Indicator(pageSize = 3, currentPage = 0)
}

@Preview
@Composable
fun IndicatorPreview2(){
    Indicator(pageSize = 3, currentPage = 1)

}
@Preview
@Composable
fun IndicatorPreview3(){
    Indicator(pageSize = 3, currentPage = 2)

}