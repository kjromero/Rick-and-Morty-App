package com.kenny.rickandmortyapp.domain.usecase

import com.kenny.rickandmortyapp.domain.model.Character
import com.kenny.rickandmortyapp.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(): Result<List<Character>> {
        return repository.getCharacters()
    }
}
