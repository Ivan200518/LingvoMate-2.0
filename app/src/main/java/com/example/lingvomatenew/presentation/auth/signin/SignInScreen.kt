package com.example.lingvomatenew.presentation.auth.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingvomatenew.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.installations.installations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


@Composable
fun SignInScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(R.color.black))) {

        val textState = remember { mutableStateOf("") }

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
            text = stringResource(R.string.welcome_login),
            color = colorResource(R.color.white),
            fontSize = 28.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            text = stringResource(R.string.login_to_account),
            color = colorResource(R.color.c_gray),
            modifier = Modifier.fillMaxWidth()
        )


       Column(modifier = Modifier.padding(16.dp)) {

           Text(
               fontSize = 20.sp,
               textAlign = TextAlign.Left,
               text = stringResource(R.string.email_login),
               color = colorResource(R.color.f_white),
               modifier = Modifier.fillMaxWidth()
           )
           OutlinedTextField(onValueChange = {
               textState.value = it
           }, value = textState.value, label = {
               Text(stringResource(R.string.email_login), color = colorResource(R.color.c_gray))
           }, modifier = Modifier
               .fillMaxWidth()
               .padding(top = 4.dp),colors = TextFieldDefaults.colors(
                   unfocusedContainerColor = colorResource(R.color.f_white)))
           Text(
               fontSize = 20.sp,
               textAlign = TextAlign.Left,
               text = stringResource(R.string.login_password),
               color = colorResource(R.color.f_white),
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(top = 10.dp)
           )

           OutlinedTextField(onValueChange = {
               textState.value = it
           }, value = textState.value, label = {
               Text(stringResource(R.string.login_password), color = colorResource(R.color.c_gray))
           }, modifier = Modifier
               .fillMaxWidth()
               .padding(top = 4.dp),
               visualTransformation = PasswordVisualTransformation(),colors = TextFieldDefaults.colors(
                   unfocusedContainerColor = colorResource(R.color.f_white)))

           Text(
               fontSize = 20.sp,
               textAlign = TextAlign.Right,
               text = stringResource(R.string.forgot_password),
               color = colorResource(R.color.password_red),
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(top = 10.dp)
           )
            Spacer(modifier = Modifier.height(40.dp))
           OutlinedButton(
               onClick = {
                   // Ваш код при нажатии кнопки
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
           Spacer(modifier = Modifier.height(10.dp))

            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                Text(
                    fontSize = 20.sp,
                    text = stringResource(R.string.not_account),
                    color = colorResource(R.color.ac_gray),
                    modifier = Modifier
                        .padding(top = 10.dp, end = 10.dp)
                )
                Text(
                    fontSize = 20.sp,
                    text = "Create Now",
                    color = colorResource(R.color.d_white),
                    modifier = Modifier
                        .padding(top = 10.dp)
                )

            }
           Spacer(modifier = Modifier.height(35.dp))

           Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()){
               Image(
                   painter = painterResource(R.drawable.google),
                   contentDescription = "login_picture",)

               Spacer(modifier = Modifier.width(50.dp))
               Image(
                   painter = painterResource(R.drawable.instagram),
                   contentDescription = "login_picture", modifier = Modifier.height(30.dp).width(32.dp))
           }
       }
    }
}


@Preview
@Composable
fun SignInPreview() {
    SignInScreen()
}
