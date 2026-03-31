package com.kenny.rickandmortyapp.presentation.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kenny.rickandmortyapp.domain.model.Character
import com.kenny.rickandmortyapp.presentation.components.CharacterItem
import com.kenny.rickandmortyapp.presentation.components.ErrorScreen
import com.kenny.rickandmortyapp.presentation.components.LoadingScreen

@Composable
fun CharactersScreen(viewModel: CharactersViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is CharactersUiState.Loading -> LoadingScreen()
        is CharactersUiState.Success -> CharacterList(characters = state.characters)
        is CharactersUiState.Error -> ErrorScreen(
            message = state.message,
            onRetry = viewModel::onRetry
        )
    }
}

@Composable
private fun CharacterList(characters: List<Character>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(characters, key = { it.id }) { character ->
            CharacterItem(character = character)
        }
    }
}
