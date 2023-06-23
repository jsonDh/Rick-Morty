package com.json.rick_morty.presentation.view.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.json.rick_morty.presentation.view.ui.theme.RickMortyTheme
import com.json.rick_morty.presentation.viewmodel.CharactersListState
import com.json.rick_morty.presentation.viewmodel.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RickMortyActivity : ComponentActivity() {

    private val charactersViewModel: CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickMortyTheme {

            }
        }
    }
}


