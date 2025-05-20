package com.example.lingvomatenew.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lingvomatenew.R
import com.example.lingvomatenew.presentation.home.HomeState
import com.example.lingvomatenew.presentation.main.RouteScreens
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProfileScreen(navController: NavController) {

    val viewModel : ProfileViewModel = hiltViewModel()
    val profileState = viewModel.state.collectAsState()
    LaunchedEffect(true) {
        viewModel.state.collectLatest {
            if (it == ProfileState.SuccessLogOut) {
                navController.navigate(RouteScreens.LOGIN)
                viewModel.resetProfileState()
            }
        }
    }

    Spacer(modifier = Modifier.height(80.dp))
    Scaffold(containerColor = colorResource(R.color.black_12)) {
        Column(modifier = Modifier.fillMaxWidth().padding(it).fillMaxSize()) {
            Image(
                modifier = Modifier.fillMaxWidth().height(150.dp).padding(15.dp).align(Alignment.CenterHorizontally),
                painter = painterResource(R.drawable.profile),
                contentDescription = "profile"
            )

            Spacer(modifier = Modifier.height(16.dp))
            Box {
                OutlinedButton(
                    modifier = Modifier.align(Alignment.BottomCenter).padding(horizontal = 30.dp)
                        .fillMaxWidth()
                        .padding(end = 30.dp, start = 30.dp)
                        .height(48.dp),
                    onClick = {
                        viewModel.signOut()
                        if (profileState.value == ProfileState.SuccessLogOut) {
                            navController.navigate(RouteScreens.LOGIN)
                        }
                    },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = colorResource(R.color.white),
                        containerColor = colorResource(R.color.blue)
                    ),

                    ) {
                    Text(text = stringResource(R.string.log_out), fontSize = 20.sp)
                }
            }
        }

    }
}