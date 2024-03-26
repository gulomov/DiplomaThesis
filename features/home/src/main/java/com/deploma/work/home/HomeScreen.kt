package com.deploma.work.home

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import org.slf4j.LoggerFactory

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val selectedRingtoneName = remember { mutableStateOf<String?>(null) }

    val log: org.slf4j.Logger? = LoggerFactory.getLogger("HomeScreen")
    Button(
        onClick = {
            log?.info("Open System Notification Sound Settings button clicked")
        },
        content = {
            Text("Open System Notification Sound Settings")
        },
    )
}
