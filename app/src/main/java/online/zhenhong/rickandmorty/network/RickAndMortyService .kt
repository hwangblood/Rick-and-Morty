package online.zhenhong.rickandmorty.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * A public interface that exposes the Rick and Morty API methods.
 */
interface RickAndMortyService {

    /**
     * Get a character from the api by its id.
     * @param id The unique identification of character
     * @return Character response of [CharacterResponse] type
     */
    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<CharacterResponse>
}
