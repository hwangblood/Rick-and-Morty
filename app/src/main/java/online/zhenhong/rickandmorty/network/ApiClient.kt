package online.zhenhong.rickandmorty.network

import online.zhenhong.rickandmorty.network.models.CharacterResponse
import online.zhenhong.rickandmorty.network.models.CharactersPageResponse
import online.zhenhong.rickandmorty.network.models.EpisodeResponse
import retrofit2.Response

class ApiClient(private val rickAndMortyService: RickAndMortyService) {

    suspend fun getCharacterById(characterId: Int): NetworkResponse<CharacterResponse> {
        return safeApiCall { rickAndMortyService.getCharacterById(characterId) }
    }

    suspend fun getCharactersByPage(pageIndex: Int): NetworkResponse<CharactersPageResponse> {
        return safeApiCall { rickAndMortyService.getCharactersByPage(pageIndex) }
    }

    suspend fun getEpisodeById(episodeId: Int): NetworkResponse<EpisodeResponse> {
        return safeApiCall { rickAndMortyService.getEpisodeById(episodeId) }
    }

    suspend fun getEpisodesByRange(range: String): NetworkResponse<List<EpisodeResponse>> {
        return safeApiCall { rickAndMortyService.getEpisodesByRange(range) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): NetworkResponse<T> {
        return try {
            NetworkResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            NetworkResponse.failure(e)
        }
    }

}
