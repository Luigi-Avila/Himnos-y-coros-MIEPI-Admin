package com.luigidev.himnosycorosmiepiadmin.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.luigidev.himnosycorosmiepiadmin.R
import com.luigidev.himnosycorosmiepiadmin.navigation.Routes
import com.luigidev.himnosycorosmiepiadmin.theme.Purple40
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(key1 = true){
        delay(2000)
        navController.popBackStack()
        navController.navigate(Routes.HomeScreen.route)
    }
    Splash()
}

@Composable
fun Splash() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.church_animation))
    val progress by animateLottieCompositionAsState(composition = composition)

    Box(
        Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else Purple40)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(composition = composition, progress = { progress })
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    Splash()
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenDarkPreview() {
    Splash()
}

