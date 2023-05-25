package com.hwangblood.rickandmorty.ui.charcter.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hwangblood.rickandmorty.domain.models.Character
import com.hwangblood.rickandmorty.repository.CharacterRepository
import kotlinx.coroutines.launch

class CharacterDetailViewModel : ViewModel() {
    private val repository by lazy { CharacterRepository() }

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character>
        get() = _character

    fun refreshCharacter(characterId: Int) = viewModelScope.launch {
        val character = repository.getCharacterById(characterId)
        _character.run { postValue(character) }
    }
}