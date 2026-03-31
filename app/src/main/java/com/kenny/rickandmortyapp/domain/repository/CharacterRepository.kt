package com.kenny.rickandmortyapp.domain.repository

import com.kenny.rickandmortyapp.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacters(): Result<List<Character>>
}
