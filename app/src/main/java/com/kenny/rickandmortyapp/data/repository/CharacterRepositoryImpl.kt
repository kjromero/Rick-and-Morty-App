package com.kenny.rickandmortyapp.data.repository

import com.kenny.rickandmortyapp.data.remote.api.RickAndMortyApi
import com.kenny.rickandmortyapp.data.remote.mapper.toDomain
import com.kenny.rickandmortyapp.domain.model.Character
import com.kenny.rickandmortyapp.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi
) : CharacterRepository {

    override suspend fun getCharacters(): Result<List<Character>> = runCatching {
        val response = api.getCharacters()
        response.results.map { it.toDomain() }
    }
}