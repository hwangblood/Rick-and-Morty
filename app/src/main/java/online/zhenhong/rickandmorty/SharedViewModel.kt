package online.zhenhong.rickandmorty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import online.zhenhong.rickandmorty.network.CharacterResponse

class SharedViewModel : ViewModel() {
    private val repository by lazy { SharedRepository() }

    private val _character = MutableLiveData<CharacterResponse>()
    val character: LiveData<CharacterResponse>
        get() = _character

    fun refreshCharacter(characterId: Int) {
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)
            response?.let { _character.postValue(it) }
        }
    }
}