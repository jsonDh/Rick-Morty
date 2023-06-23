package com.json.rick_morty.presentation.view.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.json.rick_morty.R


@Composable
fun ShowLoading() {
    LinearProgressIndicator(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary,
        trackColor = MaterialTheme.colorScheme.onBackground,
    )
}

@Composable
fun LargeAppBar(title : String){
    LargeTopAppBar(
        title = {
            Text(text = title)
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun SmallAppBar(){
    TopAppBar(
        title = {
            Text(stringResource(id = R.string.app_name))
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun ShowError(message: String) {
    Log.e("SNACK-BAR", message)
}

