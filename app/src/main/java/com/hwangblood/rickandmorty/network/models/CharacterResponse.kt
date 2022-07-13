package com.hwangblood.rickandmorty.network.models

import com.squareup.moshi.Json

/**
 * Character for json response from web api, parsing from MoshiConverter of retrofit.
 */
data class CharacterResponse(
    @Json(name = "created")
    val created: String = "",
    @Json(name = "episode")
    val episode: List<String> = listOf(),
    @Json(name = "gender")
    val gender: String = "",
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "image")
    val image: String = "",
    @Json(name = "location")
    val location: Location = Location(),
    @Json(name = "name")
    val name: String = "",
    @Json(name = "origin")
    val origin: Location = Location(),
    @Json(name = "species")
    val species: String = "",
    @Json(name = "status")
    val status: String = "",
    @Json(name = "type")
    val type: String = "",
    @Json(name = "url")
    val url: String = ""
) {
    /**
     * Location info for [CharacterResponse]'s origin and location properties.
     */
    data class Location(
        @Json(name = "name")
        val name: String = "",
        @Json(name = "url")
        val url: String = ""
    )
}
