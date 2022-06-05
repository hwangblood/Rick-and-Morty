package online.zhenhong.rickandmorty.domain.mappers

import online.zhenhong.rickandmorty.domain.models.Episode
import online.zhenhong.rickandmorty.network.models.EpisodeResponse

object EpisodeMapper {

    fun buildFrom(response: EpisodeResponse): Episode {
        return Episode(
            airDate = response.airDate,
            episode = response.episode,
            id = response.id,
            name = response.name
        )
    }
}