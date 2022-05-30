package online.zhenhong.rickandmorty.charcters

import online.zhenhong.rickandmorty.network.CharacterResponse

class CharactersRepository {
    suspend fun getCharacterList(pageIndex: Int): List<CharacterResponse> {

        return emptyList()
    }
}