package com.diploma.work.design.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.diploma.work.composables.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThesisTopBar(
    backArrowVisibility: Boolean,
    searchingVisibility: Boolean,
    onSearchClicked: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White,
        ),
        title = {
            Text(text = stringResource(R.string.title_sales))
        },
        navigationIcon = {
            if (backArrowVisibility) {
                IconButton(
                    onClick = { navController.popBackStack() },
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                    )
                }
            }
        },
        actions = {
            if (searchingVisibility) {
                IconButton(onClick = onSearchClicked) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                    )
                }
            }
        },
    )
}

@PreviewLightDark
@Composable
private fun ThesisTopBarPreview() {
    MaterialTheme {
        ThesisTopBar(
            backArrowVisibility = true,
            searchingVisibility = true,
            onSearchClicked = {}
        )
    }
}