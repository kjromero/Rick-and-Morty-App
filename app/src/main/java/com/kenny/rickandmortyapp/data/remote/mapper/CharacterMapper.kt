package com.kenny.rickandmortyapp.data.remote.mapper

import com.kenny.rickandmortyapp.data.remote.dto.CharacterDto
import com.kenny.rickandmortyapp.domain.model.Character
import com.kenny.rickandmortyapp.domain.model.CharacterStatus

fun CharacterDto.toDomain(): Character {
    return Character(
        id = id,
        name = name,
        status = when (status.lowercase()) {
            "alive" -> CharacterStatus.ALIVE
            "dead" -> CharacterStatus.DEAD
            else -> CharacterStatus.UNKNOWN
        },
        species = species,
        imageUrl = image
    )
}