package online.zhenhong.rickandmorty.charcter

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import online.zhenhong.rickandmorty.network.models.CharacterResponse
import online.zhenhong.rickandmorty.repository.CharacterRepository

class CharactersDataSource(
    private val coroutineScope: CoroutineScope,
    private val repository: CharacterRepository
) : PageKeyedDataSource<Int, CharacterResponse>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CharacterResponse>
    ) {
        coroutineScope.launch {
            val page = repository.getCharactersByPage(1)

            if (page == null) {
                callback.onResult(emptyList(), null, null)
                return@launch
            }
            callback.onResult(page.results, null, page.nextPageIndex)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CharacterResponse>
    ) {
        // nothing to do
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CharacterResponse>
    ) {
        coroutineScope.launch {
            val page = repository.getCharactersByPage(params.key)

            if (page == null) {
                callback.onResult(emptyList(), null)
                return@launch
            }
            callback.onResult(page.results, page.nextPageIndex)
        }
    }
}