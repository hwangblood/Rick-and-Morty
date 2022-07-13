package com.hwangblood.rickandmorty.domain.mappers

import com.hwangblood.rickandmorty.domain.models.Episode
import com.hwangblood.rickandmorty.network.models.EpisodeResponse

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