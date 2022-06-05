package online.zhenhong.rickandmorty.domain.mappers

import online.zhenhong.rickandmorty.domain.models.Character
import online.zhenhong.rickandmorty.network.models.CharacterResponse
import online.zhenhong.rickandmorty.network.models.EpisodeResponse

object CharacterMapper {
    fun buildFrom(response: CharacterResponse, episodes: List<EpisodeResponse>): Character {
        return Character(
            episodeList = episodes.map(EpisodeMapper::buildFrom),
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