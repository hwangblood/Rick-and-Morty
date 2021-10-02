package online.zhenhong.rickandmorty.network

import retrofit2.Response

class ApiClient(private val rickAndMortyService: RickAndMortyService) {

    suspend fun getCharacterById(characterId: Int): Response<CharacterResponse> {
        return rickAndMortyService.getCharacterById(characterId)
    }

}
