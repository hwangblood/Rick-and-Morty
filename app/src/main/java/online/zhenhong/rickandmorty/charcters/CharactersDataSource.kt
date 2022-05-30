package online.zhenhong.rickandmorty.charcters

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import online.zhenhong.rickandmorty.network.CharacterResponse

class CharactersDataSource(
    private val coroutineScope: CoroutineScope,
    private val charactersRepository: CharactersRepository
) : PageKeyedDataSource<Int, CharacterResponse>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CharacterResponse>
    ) {
        coroutineScope.launch {
            val characterList = charactersRepository.getCharacterList(1)
            callback.onResult(characterList, null, 2)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CharacterResponse>
    ) {
        coroutineScope.launch {
            val characterList = charactersRepository.getCharacterList(params.key)
            callback.onResult(characterList, params.key + 1)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CharacterResponse>
    ) {
        // nothing to do
    }
}