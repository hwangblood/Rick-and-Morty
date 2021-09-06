package online.zhenhong.rickandmorty.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val RICK_AND_MORTY_API_BASE_URL = "https://rickandmortyapi.com/api/"

private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(RICK_AND_MORTY_API_BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface RickAndMortyService {
    @GET("character/{id}")
    fun getCharacterById(@Path("id") id: Int): Call<Any>
}

object RickAndMortyApiService {
    val service: RickAndMortyService by lazy { retrofit.create(RickAndMortyService::class.java) }
}
