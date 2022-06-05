package online.zhenhong.rickandmorty.domain.mappers

import online.zhenhong.rickandmorty.domain.models.Character
import online.zhenhong.rickandmorty.network.models.CharacterResponse

object CharacterMapper {
    fun buildFrom(response: CharacterResponse): Character {
        return Character(
            episodeList = emptyList(),// TODO: convert episode string list to object list
            gender = response.gender,
            id = response.id,
            image = response.image,
            location = Character.Location(
                name = response.location.name,
                url = response.location.url
            ),
            name = response.name,
            origin = Character.Location(
                name = response.origin.name,
                url = response.origin.url
            ),
            species = response.species,
            status = response.status,
            type = response.type
        )
    }
}