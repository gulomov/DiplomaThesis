package com.deploma.work.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.deploma.work.screens.R
import com.deploma.work.screens.ScreenRoute
import com.deploma.work.screens.graph.IntroNav
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(durationMillis = 800, easing = {
                OvershootInterpolator(4f).getInterpolation(it)
            }),
        )
        delay(1000L)
        navController.navigate(ScreenRoute.Home.route) {
            popUpTo(IntroNav.INTRO_ROUTE)
        }
    }

    Scaffold() { innerPaddingModifier ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
                .padding(innerPaddingModifier),
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "Logo",
                modifier = Modifier.scale(scale.value),
            )
        }
    }
}
