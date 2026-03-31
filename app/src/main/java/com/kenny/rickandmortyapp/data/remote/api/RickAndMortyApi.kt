package com.kenny.rickandmortyapp.data.remote.api

import com.kenny.rickandmortyapp.data.remote.dto.CharacterResponseDto
import retrofit2.http.GET

interface RickAndMortyApi {
    @GET("character")
    suspend fun getCharacters(): CharacterResponseDto
}