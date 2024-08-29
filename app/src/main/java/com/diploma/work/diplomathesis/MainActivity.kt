package com.diploma.work.diplomathesis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.diploma.work.design.theme.DiplomaThesisTheme
import com.diploma.work.diplomathesis.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            enableEdgeToEdge(
                statusBarStyle = systemBarStyle(),
                navigationBarStyle = systemBarStyle()
            )
            DiplomaThesisTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    AppNavHost()
                }
            }
        }
    }
}

@Composable
private fun systemBarStyle(): SystemBarStyle {
    return if (isSystemInDarkTheme()) {
        SystemBarStyle.dark(
            Color.Transparent.hashCode()
        )
    } else {
        SystemBarStyle.light(
            Color.Transparent.hashCode(),
            Color.Black.hashCode(),
        )
    }
}