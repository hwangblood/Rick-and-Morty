package online.zhenhong.rickandmorty.network

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import online.zhenhong.rickandmorty.MortyApplication
import online.zhenhong.rickandmorty.MortyApplication.Companion.context
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object NetworkLayer {

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
        .client(getLoggingHttpClient())
        .baseUrl(RICK_AND_MORTY_API_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    /**
     * A public object that exposes the lazy-initialized Retrofit service
     */
    private val rickAndMortyService: RickAndMortyService by lazy {
        retrofit.create(
            RickAndMortyService::class.java
        )
    }

    val apiClient = ApiClient(rickAndMortyService)

    private fun getLoggingHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        })

        builder.addInterceptor(
            ChuckerInterceptor.Builder(MortyApplication.context)
                .collector(ChuckerCollector(context))
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()
        )

        return builder.build()
    }
}
