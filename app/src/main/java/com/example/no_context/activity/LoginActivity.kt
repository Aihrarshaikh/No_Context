package com.example.no_context

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.no_context.activity.ChannelListActivity
import com.example.no_context.ui.theme.No_contextTheme
import com.example.no_context.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    private val viewModel : LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeToEvents()

        setContent {
            No_contextTheme {
                LoginScreen()
            }
        }
    }
    @Composable
    fun LoginScreen(){

        var username by remember{
            mutableStateOf(TextFieldValue(""))
        }

        var showProgress :Boolean by remember {
            mutableStateOf(false)
        }
        viewModel.loadingState.observe(this, Observer {
            UiLoadingState ->
            showProgress = when(UiLoadingState){
                is LoginViewModel.UiLoadingState.Loading -> {
                    true
                }
                is LoginViewModel.UiLoadingState.NotLoading -> {
                    false
                }
            }
        })
        ConstraintLayout(modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF0D1117))
            .padding(start = 35.dp, end = 35.dp)) {
            val(
            logo,usernameTextField,btnLoginAsUser,
            btnLoginAsGuest,progressBar,sugg
            ) = createRefs()
            Image(painter = painterResource(id = R.drawable.logo), contentDescription ="logo",
            modifier = Modifier
                .height(120.dp)
                .width(120.dp)
                .constrainAs(logo) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top, margin = 100.dp)
                }
            )
            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Gray,
                    focusedBorderColor = Color(0xFF00FF00),
                    textColor = Color.White
                ),
                value = username, onValueChange ={
                newValue -> username = newValue
            },
            label = { Text(text = "Enter unknown1 or unknown2....unknown10", style = TextStyle(color = Color.Gray)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(usernameTextField) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(logo.bottom, margin = 32.dp)
                    },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

            Button(onClick = {
                                if(username.text.trim() == "cybot"){
                                    viewModel.loginUser(username.text,getString(R.string.jwt_token1))
                                }
                if(username.text.trim() == "CYBOT"){
                                    viewModel.loginUser(
                                        username.text,
                                        getString(R.string.jwt_token)
                                    )
                                }
                if(username.text.trim() == "arjun"){
                                    viewModel.loginUser(
                                        username.text,
                                        getString(R.string.jwt_token2)
                                    )
                                }
                if(username.text.trim() == "palash"){
                                    viewModel.loginUser(
                                        username.text,
                                        getString(R.string.jwt_token3)
                                    )
                                }
                if(username.text.trim() == "unknown1"){
                                    viewModel.loginUser(
                                        username.text,
                                        getString(R.string.un1)
                                    )
                                }
                                if(username.text.trim() == "unknown2"){
                                    viewModel.loginUser(
                                        username.text,
                                        getString(R.string.un2)
                                    )
                                }
                if(username.text.trim() == "unknown3"){
                                    viewModel.loginUser(
                                        username.text,
                                        getString(R.string.un3)
                                    )
                                }
                if(username.text.trim() == "unknown4"){
                                    viewModel.loginUser(
                                        username.text,
                                        getString(R.string.un4)
                                    )
                                }

                                 if(username.text.trim() == "unknown5"){
                                    viewModel.loginUser(
                                        username.text,
                                        getString(R.string.un5)
                                    )
                                }

                                 if(username.text.trim() == "unknown6"){
                                    viewModel.loginUser(
                                        username.text,
                                        getString(R.string.un6)
                                    )
                                }
                if(username.text.trim() == "unknown7"){
                                    viewModel.loginUser(
                                        username.text,
                                        getString(R.string.un7)
                                    )
                                }

                                 if(username.text.trim() == "unknown8"){
                                    viewModel.loginUser(
                                        username.text,
                                        getString(R.string.un8)
                                    )
                                }

                                 if(username.text.trim() == "unknown9"){
                                    viewModel.loginUser(
                                        username.text,
                                        getString(R.string.un9)
                                    )
                                }

                                 if(username.text.trim() == "unknown10"){
                                    viewModel.loginUser(
                                        username.text,
                                        getString(R.string.un10)
                                    )
                                }
            },
                colors = buttonColors(backgroundColor = Color(0XFFFF772A)),
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(btnLoginAsUser) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(usernameTextField.bottom, margin = 16.dp)
                }
                ) {
                    Text(text = "jump into the unknown world..")
            }
//
            if(showProgress){
                CircularProgressIndicator(
                    modifier = Modifier.constrainAs(progressBar){
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(btnLoginAsUser.bottom, margin = 16.dp)
                    }
                )
            }
        }
    }
    private fun subscribeToEvents(){
        lifecycleScope.launchWhenStarted {
            viewModel.loginEvent.collect{event->
                when(event){
                    is LoginViewModel.LoginEvent.ErrorInputTooShort -> {
                        showToast("Invalid! Enter more than 0 characters")
                    }
                    is LoginViewModel.LoginEvent.ErrorLogIn->{
                        val errorMessage = event.error
                        showToast("Enter: $errorMessage")
                    }
                    is LoginViewModel.LoginEvent.Success ->{
                        showToast("Login Successful")
                        startActivity(Intent(this@LoginActivity,ChannelListActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }
    private fun showToast(msg: String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
}

