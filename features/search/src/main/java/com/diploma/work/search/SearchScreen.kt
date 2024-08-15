package com.diploma.work.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diploma.work.design.theme.normal100
import com.diploma.work.design.theme.small100
import com.diploma.work.design.theme.small150
import com.diploma.work.design.theme.small50
import timber.log.Timber

@Composable
fun SearchScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    Column(modifier = modifier.fillMaxSize().padding(normal100)) {
        SearchField(onSearchClicked = viewModel::onSearch)
        SearchContent(productsList = uiState.products, onProductClicked = {})

    }
}

@Composable
private fun SearchField(
    onSearchClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(small100)
    ) {
        Row(
            modifier = Modifier.padding(small100),
            horizontalArrangement = Arrangement.Center,
        ) {
            TextField(
                maxLines = 1,
                value = searchQuery,
                modifier = Modifier.weight(1f),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                    )
                },
                onValueChange = {
                    searchQuery = it
                    onSearchClicked(searchQuery)
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        text = stringResource(R.string.search),
                        color = Color.White
                    )
                }
            )
//            TextButton(
//                modifier = Modifier
//                    .padding(small50)
//                    .background(
//                        color = MaterialTheme.colorScheme.primary,
//                        shape = RoundedCornerShape(small100)
//                    ),
//                onClick = {},
//            ) {
//                Text(
//                    modifier = Modifier.padding(horizontal = small150),
//                    text = stringResource(R.string.find),
//                    color = MaterialTheme.colorScheme.onSecondary
//                )
//            }
        }
    }
}

@PreviewLightDark
@Composable
private fun SearchScreenPreview() {
    MaterialTheme {
        SearchScreen(
            navController = rememberNavController()
        )
    }
}