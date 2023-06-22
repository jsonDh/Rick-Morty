package com.json.rick_morty.presentation.view.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.lifecycleScope
import com.json.rick_morty.R
import com.json.rick_morty.presentation.view.components.ShowCharactersList
import com.json.rick_morty.presentation.view.ui.theme.RickMortyTheme
import com.json.rick_morty.presentation.viewmodel.CharactersState
import com.json.rick_morty.presentation.viewmodel.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersListActivity : ComponentActivity() {

    private val charactersViewModel: CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickMortyTheme {
                CharacterListScreen(charactersViewModel)
            }
        }
        lifecycleScope.launch {
            charactersViewModel.charactersState.collect { charactersState ->
                if (charactersState is CharactersState.Initial) {
                    charactersViewModel.getCharacters()
                }
            }
        }
    }
}

@Composable
fun CharacterListScreen(charactersViewModel: CharactersViewModel) {
    val charactersState by charactersViewModel.charactersState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                ShowCharactersList(charactersState)
            }
        }
    )
}


