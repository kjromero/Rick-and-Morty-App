package com.kenny.rickandmortyapp.domain.model

data class Character(
    val id: Int,
    val name: String,
    val status: CharacterStatus,
    val species: String,
    val imageUrl: String
)

enum class CharacterStatus {
    ALIVE, DEAD, UNKNOWN
}