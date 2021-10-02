package online.zhenhong.rickandmorty.network

import retrofit2.Response

class ApiClient(private val rickAndMortyService: RickAndMortyService) {

    suspend fun getCharacterById(characterId: Int): NetworkResponse<CharacterResponse> {
        return safeApiCall { rickAndMortyService.getCharacterById(characterId) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): NetworkResponse<T> {
        return try {
            NetworkResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            NetworkResponse.failure(e)
        }
    }

}
