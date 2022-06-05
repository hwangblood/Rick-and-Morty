package online.zhenhong.rickandmorty.charcter

import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope
import online.zhenhong.rickandmorty.repository.CharacterRepository

class CharactersDataSourceFactory(
    private val coroutineScope: CoroutineScope,
    private val repository: CharacterRepository
) : DataSource.Factory<Int, CharacterResponse>() {

    override fun create(): DataSource<Int, CharacterResponse> {
        return CharactersDataSource(coroutineScope, repository)
    }
}