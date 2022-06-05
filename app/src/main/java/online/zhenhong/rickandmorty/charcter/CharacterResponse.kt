package online.zhenhong.rickandmorty.charcter

/**
 * Character for json response from web api, parsing from MoshiConverter of retrofit.
 */
data class CharacterResponse(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Location,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) {
    /**
     * Location info for [CharacterResponse]'s origin and location properties.
     */
    data class Location(
        val name: String,
        val url: String
    )
}
