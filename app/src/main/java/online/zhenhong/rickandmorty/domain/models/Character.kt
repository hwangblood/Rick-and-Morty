package online.zhenhong.rickandmorty.domain.models


data class Character(
    val episodeList: List<Episode> = listOf(),
    val gender: String = "",
    val id: Int = 0,
    val image: String = "",
    val location: Location = Location(),
    val name: String = "",
    val origin: Location = Location(),
    val species: String = "",
    val status: String = "",
    val type: String = "",
) {
    data class Location(
        val name: String = "",
        val url: String = ""
    )
}
