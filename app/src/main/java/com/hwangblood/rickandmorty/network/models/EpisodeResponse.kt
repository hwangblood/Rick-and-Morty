package com.hwangblood.rickandmorty.network.models

import com.squareup.moshi.Json

data class EpisodeResponse(
    @Json(name = "air_date")
    val airDate: String = "",
    @Json(name = "characters")
    val characters: List<String> = listOf(),
    @Json(name = "created")
    val created: String = "",
    @Json(name = "episode")
    val episode: String = "",
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "url")
    val url: String = ""
)