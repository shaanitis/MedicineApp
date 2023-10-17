package com.example.materialcompose.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.materialcompose.R
import com.example.materialcompose.ui.theme.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {

    var emailText by remember { mutableStateOf("") }
    var passText by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(it)
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            val isPlaying by remember { mutableStateOf(true) }
            val speed by remember { mutableStateOf(3f) }

            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.medicine))
            val progress by animateLottieCompositionAsState(
                composition,
                iterations = LottieConstants.IterateForever,
                isPlaying = isPlaying,
                speed = speed,
                restartOnPlay = true
            )
            LottieAnimation(
                composition,
                progress,
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(40.dp))
            TextField(modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .padding(horizontal = 20.dp, vertical = 10.dp),
                value = emailText,
                onValueChange = {
                    emailText = it
                },
                placeholder = { Text(text = "Enter email") })
            TextField(modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .padding(horizontal = 20.dp, vertical = 10.dp),
                value = passText,
                onValueChange = {
                    passText = it
                },
                placeholder = { Text(text = "Enter password") })
            Spacer(modifier = Modifier.height(40.dp))
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .background(
                    color= MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(50.dp)
                ),
                onClick = {
                    if (emailText.trim().isEmpty() || passText.trim().isEmpty()) {
                        Toast.makeText(context, "Fill in the fields first", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        navController.navigate("new_screen/$emailText")
                    }
                }) {
                Text(text = "Got It", style = TextStyle(fontFamily = poppins), color = Color.White)
            }
        }


    }
}



