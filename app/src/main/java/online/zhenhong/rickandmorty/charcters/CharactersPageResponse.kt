package online.zhenhong.rickandmorty.charcters

import com.squareup.moshi.Json
import online.zhenhong.rickandmorty.network.CharacterResponse


class CharactersPageResponse(
    @Json(name = "info")
    val info: Info = Info(),
    @Json(name = "results")
    val results: List<CharacterResponse> = emptyList()
) {
    data class Info(
        @Json(name = "count")
        val count: Int = 0,
        @Json(name = "next")
        val next: String? = null,
        @Json(name = "pages")
        val pages: Int = 0,
        @Json(name = "prev")
        val prev: String? = null
    )
}
