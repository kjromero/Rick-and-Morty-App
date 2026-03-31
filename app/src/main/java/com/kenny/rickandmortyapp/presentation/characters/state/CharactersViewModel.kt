package com.kenny.rickandmortyapp.presentation.characters.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenny.rickandmortyapp.domain.usecase.GetCharactersUseCase
import com.kenny.rickandmortyapp.presentation.characters.state.CharactersUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CharactersUiState>(CharactersUiState.Loading)
    val uiState: StateFlow<CharactersUiState> = _uiState.asStateFlow()

    init {
        getCharacters()
    }

    fun onRetry() {
        getCharacters()
    }

    private fun getCharacters() {
        _uiState.value = CharactersUiState.Loading
        viewModelScope.launch {
            getCharactersUseCase.invoke()
                .onSuccess { characters ->
                    _uiState.value = CharactersUiState.Success(characters)
                }
                .onFailure { throwable ->
                    _uiState.value = CharactersUiState.Error(
                        throwable.message ?: "Error desconocido"
                    )
                }
        }
    }
}