package com.kenny.rickandmortyapp.presentation.characters.state

import com.kenny.rickandmortyapp.domain.model.Character

sealed class CharactersUiState {
    object Loading : CharactersUiState()
    data class Success(val characters: List<Character>) : CharactersUiState()
    data class Error(val message: String) : CharactersUiState()
}