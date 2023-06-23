package com.json.rick_morty.presentation.view.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.json.rick_morty.presentation.view.components.ShowCharactersList
import com.json.rick_morty.presentation.view.components.SmallAppBar
import com.json.rick_morty.presentation.view.ui.theme.RickMortyTheme
import com.json.rick_morty.presentation.viewmodel.CharactersListState
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
            charactersViewModel.charactersListState.collect { charactersState ->
                if (charactersState is CharactersListState.Initial) {
                    charactersViewModel.getCharacters()
                }
            }
        }
    }
}

@Composable
fun CharacterListScreen(charactersViewModel: CharactersViewModel) {
    val charactersState by charactersViewModel.charactersListState.collectAsState()
    Scaffold(
        topBar = {
            SmallAppBar()
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                ShowCharactersList(charactersState)
            }
        }
    )
}


