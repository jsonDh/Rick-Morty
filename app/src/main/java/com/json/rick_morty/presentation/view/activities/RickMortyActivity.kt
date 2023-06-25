package com.json.rick_morty.presentation.view.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.json.rick_morty.presentation.view.Screens
import com.json.rick_morty.presentation.view.components.CharacterDetailFragment
import com.json.rick_morty.presentation.view.components.CharacterListFragment
import com.json.rick_morty.presentation.view.ui.theme.RickMortyTheme
import com.json.rick_morty.presentation.viewmodel.CharacterViewModel
import com.json.rick_morty.presentation.viewmodel.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RickMortyActivity : ComponentActivity() {

    private val detailsViewModel: CharacterViewModel by viewModels()
    private val listViewModel: CharactersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickMortyTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screens.CharacterListScreen.route
                ) {
                    composable(Screens.CharacterListScreen.route) {
                        CharacterListFragment(
                            charactersViewModel = listViewModel,
                            navController = navController
                        )
                    }
                    composable(Screens.CharacterDetailsScreen.route + "/{characterId}", listOf(
                        navArgument("characterId") {
                            type = NavType.StringType
                        }
                    )) {
                        val characterId = it.arguments?.getString("characterId") ?: ""
                        CharacterDetailFragment(
                            characterId,
                            characterViewModel = detailsViewModel,
                            navController = navController
                        )
                    }
                }
            }
        }
    }


}


