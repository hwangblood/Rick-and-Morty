package online.zhenhong.rickandmorty.network

import online.zhenhong.rickandmorty.network.models.CharacterResponse
import online.zhenhong.rickandmorty.network.models.CharactersPageResponse
import online.zhenhong.rickandmorty.network.models.EpisodeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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


    @GET("character")
    suspend fun getCharactersByPage(@Query("page") pageIndex: Int): Response<CharactersPageResponse>

    @GET("episode/{id}")
    suspend fun getEpisodeById(@Path("id") id: Int): Response<EpisodeResponse>

    /**
     * You can get multiple episodes by adding an array of ids as parameter:
     * /episode/[1,2,3] or /episode/1,2,3
     */
    @GET("episode/{range}")
    suspend fun getEpisodesByRange(@Path("range") range: String): Response<List<EpisodeResponse>>
}
