package com.hwangblood.rickandmorty.charcter.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.hwangblood.rickandmorty.domain.models.Character
import com.hwangblood.rickandmorty.repository.CharacterRepository

class CharacterDetailViewModel : ViewModel() {
    private val repository by lazy { CharacterRepository() }

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character>
        get() = _character

    fun refreshCharacter(characterId: Int) {
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)
            response?.let { _character.postValue(it) }
        }
    }
}