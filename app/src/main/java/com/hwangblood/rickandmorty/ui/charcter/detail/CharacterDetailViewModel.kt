package com.hwangblood.rickandmorty.ui.charcter.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hwangblood.rickandmorty.RickAndMortyCache
import com.hwangblood.rickandmorty.domain.models.Character
import com.hwangblood.rickandmorty.repository.CharacterRepository
import kotlinx.coroutines.launch

class CharacterDetailViewModel : ViewModel() {
    private val repository by lazy { CharacterRepository() }

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character>
        get() = _character

    fun refreshCharacter(characterId: Int) {
        // Check the cache for our character
        val cachedCharacter = RickAndMortyCache.characterMap[characterId]
        if (cachedCharacter != null) {
            _character.postValue(cachedCharacter)
            return
        }

        // Otherwise, we need to make the network call for the character
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)
            response?.let {
                _character.postValue(it)

                // Update cache if non-null char received
                RickAndMortyCache.characterMap[characterId] = it
            }

        }
    }
}