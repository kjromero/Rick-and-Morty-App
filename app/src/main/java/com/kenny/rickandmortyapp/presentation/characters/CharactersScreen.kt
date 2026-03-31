package com.kenny.rickandmortyapp.presentation.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(viewModel: CharactersViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Rick & Morty Characters")
                }
            )
        }
    ) { innerPadding ->
        when (val state = uiState) {
            is CharactersUiState.Loading -> LoadingScreen()
            is CharactersUiState.Success -> CharacterList(
                characters = state.characters,
                contentPadding = innerPadding
            )
            is CharactersUiState.Error -> ErrorScreen(
                message = state.message,
                onRetry = viewModel::onRetry
            )
        }
    }
}

@Composable
private fun CharacterList(characters: List<Character>, contentPadding: PaddingValues) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = contentPadding.calculateTopPadding() + 8.dp,
            bottom = contentPadding.calculateBottomPadding() + 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(characters, key = { it.id }) { character ->
            CharacterItem(character = character)
        }
    }
}
