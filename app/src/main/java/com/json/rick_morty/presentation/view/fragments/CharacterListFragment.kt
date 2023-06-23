package com.json.rick_morty.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.json.rick_morty.presentation.view.components.ShowCharactersList
import com.json.rick_morty.presentation.view.components.SmallAppBar
import com.json.rick_morty.presentation.view.ui.theme.RickMortyTheme
import com.json.rick_morty.presentation.viewmodel.CharactersListState
import com.json.rick_morty.presentation.viewmodel.CharactersViewModel
import kotlinx.coroutines.launch

class CharacterListFragment : Fragment() {

    private val listViewModel : CharactersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lifecycleScope.launch {
            listViewModel.charactersListState.collect { charactersState ->
                if (charactersState is CharactersListState.Initial) {
                    listViewModel.getCharacters()
                }
            }
        }
        return ComposeView(requireContext()).apply {
            setContent {
                RickMortyTheme {
                    CharacterListScreen(listViewModel)
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