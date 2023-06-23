package com.json.rick_morty.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.json.rick_morty.presentation.view.components.CharacterDetailStates
import com.json.rick_morty.presentation.viewmodel.CharacterState
import com.json.rick_morty.presentation.viewmodel.CharacterViewModel
import kotlinx.coroutines.launch

class CharacterDetailsFragment : Fragment() {

    private val detailsViewModel: CharacterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val characterId = requireArguments().getString("characterId") ?: ""
        lifecycleScope.launch {
            detailsViewModel.characterState.collect { characterState ->
                if (characterState is CharacterState.Initial) {
                    detailsViewModel.characterId.value = characterId
                    detailsViewModel.getCharacterDetails()
                }
            }
        }

        return ComposeView(requireContext()).apply {
            setContent {
                CharacterDetailScreen(detailsViewModel)
            }
        }
    }
}

@Composable
fun CharacterDetailScreen(characterViewModel: CharacterViewModel) {
    val charactersState by characterViewModel.characterState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back Button"
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                CharacterDetailStates(charactersState)
            }
        }
    )
}