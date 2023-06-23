package com.json.rick_morty.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.exception.ApolloException
import com.json.rick_morty.CharacterQuery
import com.json.rick_morty.domain.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val repository: CharacterRepository) :
    ViewModel() {

    private var job: Job? = null
    private val _characterState = MutableStateFlow<CharacterState>(CharacterState.Initial)
    val characterState: StateFlow<CharacterState> = _characterState
    val characterId = MutableStateFlow("")

    companion object {
        private const val TAG = "CHARACTER-VIEW-MODEL"
    }

    fun getCharacterDetails() {
        fetchCharacterDetails()
    }

    private fun fetchCharacterDetails() {
        Log.d(TAG, "Getting character details...")
        _characterState.value = CharacterState.Loading
        job = viewModelScope.launch {
            try {
                val response = repository.getCharacterDetails(characterId.value)
                withContext(Dispatchers.Main) {
                    _characterState.value = CharacterState.Success(response.data)
                    Log.d(TAG, response.data.toString())
                }
            } catch (e: ApolloException) {
                _characterState.value = CharacterState.Error(e.message.toString())
            }
        }
    }

}

sealed class CharacterState {
    object Initial : CharacterState()
    object Loading : CharacterState()
    data class Success(val data: CharacterQuery.Data?) : CharacterState()
    data class Error(val message: String) : CharacterState()
}