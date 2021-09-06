package online.zhenhong.rickandmorty.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Rick and Morty REST API
 *
 * The base url contains information about all available API's resources.
 * All requests are GET requests and go over https. All responses will return data in json.
 */
private const val RICK_AND_MORTY_API_BASE_URL = "https://rickandmortyapi.com/api/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(RICK_AND_MORTY_API_BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

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
    fun getCharacterById(@Path("id") id: Int): Call<CharacterResponse>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object RickAndMortyApiService {
    val service: RickAndMortyService by lazy { retrofit.create(RickAndMortyService::class.java) }
}
