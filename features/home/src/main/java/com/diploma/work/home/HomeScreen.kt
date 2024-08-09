package com.diploma.work.home

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diploma.work.common.componants.ProgressCircle
import com.diploma.work.common.componants.TopProductsLazyRow
import com.diploma.work.design.theme.DiplomaThesisTheme
import com.diploma.work.design.theme.small150
import com.diploma.work.home.componants.NewsInHome
import com.diploma.work.home.componants.RecommendationsInHome
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import okhttp3.internal.toImmutableList
import timber.log.Timber

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val notificationPermissionState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        rememberPermissionState(
            Manifest.permission.POST_NOTIFICATIONS
        )
    } else {
        TODO("VERSION.SDK_INT < TIRAMISU")
    }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted->
        if (isGranted) {
            Timber.d("Permission is Granted")
        }else{
            Timber.d("Permission is not Granted")
        }

    }

    LaunchedEffect(notificationPermissionState) {
        if (!notificationPermissionState.status.isGranted
            && notificationPermissionState.status.shouldShowRationale) {
            Timber.d("Permission is not Granted")
        } else {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    if (uiState.loadingValue) {
        ProgressCircle()
    } else {
        Column(
            verticalArrangement = Arrangement.spacedBy(small150),
            modifier = modifier.verticalScroll(rememberScrollState()),
        ) {
            NewsInHome(news = uiState.newsInfo.toImmutableList(), navController = navController)
            RecommendationsInHome(
                recommendations = uiState.recommendationsList,
                navController = navController
            )
            TopProductsLazyRow(
                productList = uiState.topProductsList,
                navController = navController,
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun HomeScreenPreview() {
    DiplomaThesisTheme {
        HomeScreen(rememberNavController())
    }
}