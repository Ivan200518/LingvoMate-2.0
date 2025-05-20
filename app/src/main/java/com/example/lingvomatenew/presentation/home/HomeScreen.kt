package com.example.lingvomatenew.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.lingvomatenew.R
import com.example.lingvomatenew.presentation.auth.signup.SignUpState
import com.example.lingvomatenew.presentation.main.RouteScreens
import kotlinx.coroutines.flow.collectLatest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val viewModel : HomeViewModel = hiltViewModel()
    val channels = viewModel.channels.collectAsState()
    val sheetState = rememberModalBottomSheetState()
    val addChannel = remember {
        mutableStateOf(false)
    }

    Scaffold(containerColor = (colorResource(R.color.black_12)), floatingActionButton = {
        Box(modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(R.color.blue))
            .clickable {
                addChannel.value = true
            }) {
            Text(
                text = "Add Channel", modifier = Modifier.padding(16.dp), color = Color.White
            )
        }
    }) {


        Column(modifier = Modifier.fillMaxWidth().padding(it)) {
            Image(modifier = Modifier.clickable {
                navController.navigate("profile")
            }.fillMaxWidth().height(150.dp).padding(15.dp).align(Alignment.CenterHorizontally), painter = painterResource(R.drawable.profile), contentDescription = "profile")

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth().padding(horizontal = 16.dp)) {
                    items(channels.value) { channel ->
                        Column {
                            Text(text = channel.name, color = Color.White,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(colorResource(R.color.blue))
                                    .clickable {
                                        navController.navigate("chat/${channel.id}")
                                    }
                                    .padding(16.dp))
                        }
                    }
                }
            }


    }
    if (addChannel.value) {
        ModalBottomSheet(onDismissRequest = { addChannel.value = false }, sheetState = sheetState) {
            AddChannelDialog {
                viewModel.addChannel(it)
                addChannel.value = false
            }
        }
    }

}
@Composable
fun AddChannelDialog(onAddChannel: (String) -> Unit) {
    val channelName = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Add Channel")
        Spacer(modifier = Modifier.padding(8.dp))
        TextField(value = channelName.value, onValueChange = {
            channelName.value = it
        }, label = { Text(text = "Channel Name") }, singleLine = true)
        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = { onAddChannel(channelName.value) }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Add")
        }
    }
}
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}