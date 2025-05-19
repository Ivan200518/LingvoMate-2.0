package com.example.lingvomatenew.presentation.auth.signin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lingvomatenew.R
import com.example.lingvomatenew.presentation.auth.signup.SignUpState
import com.example.lingvomatenew.presentation.auth.signup.SignUpViewModel
import com.example.lingvomatenew.presentation.main.RouteScreens


@Composable
fun SignUpScreen(navController: NavController) {
    val viewModel : SignUpViewModel  = hiltViewModel()
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(R.color.black))) {

        val userName = remember { mutableStateOf("") }

        val email = remember { mutableStateOf("") }

        val password = remember { mutableStateOf("") }
        var passwordVisible = remember { mutableStateOf(false) }

        val signUpState = viewModel.state.collectAsState()

        val errorName by  viewModel.errorMessageName.collectAsState()
        val errorEmail by  viewModel.errorMessageEmail.collectAsState()
        val errorPassword by  viewModel.errorMessagePassword.collectAsState()
        val context = LocalContext.current


        LaunchedEffect(key1 = signUpState.value) {

            when (signUpState.value) {
                is SignUpState.Success -> {
                    navController.navigate(RouteScreens.LOGIN)
                }

                is SignUpState.Error -> {
                    Toast.makeText(context, "Sign In failed", Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        }
        Image(
            painter = painterResource(R.drawable.login_picture),
            contentDescription = "login_picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),

            )
        Spacer(modifier = Modifier.height(56.dp))

        Text(
            textAlign = TextAlign.Center,
            text = stringResource(R.string.register),
            color = colorResource(R.color.white),
            fontSize = 28.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            text = stringResource(R.string.new_ac),
            color = colorResource(R.color.c_gray),
            modifier = Modifier.fillMaxWidth()
        )


        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                fontSize = 20.sp,
                textAlign = TextAlign.Left,
                text = stringResource(R.string.username),
                color = colorResource(R.color.f_white),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(onValueChange = {
                userName.value = it
                viewModel.resetErrorName()
            }, value = userName.value, label = {
                Text(text = stringResource(R.string.username_button), color = colorResource(R.color.c_gray))
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = colorResource(R.color.f_white)),
                isError = errorName,
                supportingText = {
                    if (errorName) Text("Invalid UserName", color = Color.Red, fontSize = 14.sp)
                })
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                fontSize = 20.sp,
                textAlign = TextAlign.Left,
                text = stringResource(R.string.email_login),
                color = colorResource(R.color.f_white),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(onValueChange = {
                email.value = it
                viewModel.resetErrorEmail()
            }, value = email.value, label = {
                Text(stringResource(R.string.email_login), color = colorResource(R.color.c_gray))
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(R.color.f_white)),
                isError = errorEmail, supportingText = {
                    if (errorEmail) Text("Invalid Email")
                })
            Text(
                fontSize = 20.sp,
                textAlign = TextAlign.Left,
                text = stringResource(R.string.login_password),
                color = colorResource(R.color.f_white),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )

            OutlinedTextField(   trailingIcon = {
                val image = if (passwordVisible.value)
                    Icons.Outlined.Visibility
                else
                    Icons.Outlined.VisibilityOff

                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(imageVector = image, contentDescription = if (passwordVisible.value) "Hide password" else "Show password")
                }
            },onValueChange = {
                password.value = it
                viewModel.resetErrorPassword()
            }, value = password.value, label = {
                Text(stringResource(R.string.login_password), color = colorResource(R.color.c_gray))
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
                visualTransformation = if(!passwordVisible.value) PasswordVisualTransformation() else VisualTransformation.None,colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = colorResource(R.color.f_white)),
                isError = errorPassword, supportingText = {
                    if (errorPassword) Text("Invalid Password", color = Color.Red, fontSize = 14.sp)
                })


            Spacer(modifier = Modifier.height(40.dp))
            if (signUpState.value == SignUpState.Loading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }else {
                OutlinedButton(
                    onClick = {
                        viewModel.signUp(
                            name = userName.value,
                            email = email.value,
                            password = password.value
                        )
                        if (signUpState.value == SignUpState.Success) {
                            navController.navigate(RouteScreens.HOME)
                        }
                    },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = colorResource(R.color.white),
                        containerColor = colorResource(R.color.blue)
                    ), modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 30.dp, start = 30.dp)
                        .height(48.dp)
                ) {
                    Text(text = stringResource(R.string.login_button), fontSize = 20.sp) // Текст кнопки
                }
            }
            Spacer(modifier = Modifier.height(10.dp))


            Row (modifier = Modifier.fillMaxWidth().clickable {
                navController.navigate(RouteScreens.LOGIN)
            }, horizontalArrangement = Arrangement.Center){
                Text(
                    fontSize = 20.sp,
                    text = stringResource(R.string.signup),
                    color = colorResource(R.color.ac_gray),
                    modifier = Modifier
                        .padding(top = 10.dp, end = 10.dp)
                )
                Text(
                    fontSize = 20.sp,
                    text = stringResource(R.string.login_signup),
                    color = colorResource(R.color.d_white),
                    modifier = Modifier
                        .padding(top = 10.dp)
                )

            }



        }
    }
}

//
@Preview
@Composable
fun SignUpPreview() {
    SignUpScreen(navController = rememberNavController())
}
