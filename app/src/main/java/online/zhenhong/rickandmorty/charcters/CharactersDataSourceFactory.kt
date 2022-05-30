package online.zhenhong.rickandmorty.charcters

import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope
import online.zhenhong.rickandmorty.network.CharacterResponse

class CharactersDataSourceFactory(
    private val coroutineScope: CoroutineScope,
    private val repository: CharactersRepository
) : DataSource.Factory<Int, CharacterResponse>() {

    override fun create(): DataSource<Int, CharacterResponse> {
        return CharactersDataSource(coroutineScope, repository)
    }
}