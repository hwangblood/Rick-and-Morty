package com.hwangblood.rickandmorty.repository

import com.hwangblood.rickandmorty.network.NetworkLayer
import com.hwangblood.rickandmorty.network.models.EpisodesPageResponse

class EpisodeRepository {

    suspend fun getEpisodesByPage(pageIndex: Int): EpisodesPageResponse? {
        val pageResponse = NetworkLayer.apiClient.getEpisodesByPage(pageIndex);

        if (!pageResponse.isSuccessful) {
            return null
        }
        return pageResponse.body
    }
}