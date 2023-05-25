package com.hwangblood.rickandmorty.repository

import com.hwangblood.rickandmorty.RickAndMortyCache
import com.hwangblood.rickandmorty.domain.mappers.CharacterMapper
import com.hwangblood.rickandmorty.domain.models.Character
import com.hwangblood.rickandmorty.network.NetworkLayer
import com.hwangblood.rickandmorty.network.models.CharacterResponse
import com.hwangblood.rickandmorty.network.models.CharactersPageResponse
import com.hwangblood.rickandmorty.network.models.EpisodeResponse

class CharacterRepository {

    suspend fun getCharacterById(characterId: Int): Character? {
        // Check the cache for our character
        val cachedCharacter = RickAndMortyCache.characterMap[characterId]
        if (cachedCharacter != null) {
            return cachedCharacter
        }

        val response = NetworkLayer.apiClient.getCharacterById(characterId)
        if (!response.isSuccessful || response.isFailed) {
            return null
        }
        val episodeResponseList = getEpisodesFromCharacterResponse(response.body)

        val character = CharacterMapper.buildFrom(
            response.body,
            episodeResponseList
        )
        // Update cache of the character
        RickAndMortyCache.characterMap[characterId] = character
        return character
    }

    private suspend fun getEpisodesFromCharacterResponse(characterResponse: CharacterResponse): List<EpisodeResponse> {
        val episodeRange = characterResponse.episode.map {
            it.substring(startIndex = it.lastIndexOf("/") + 1)
        }.toString()

        val response = NetworkLayer.apiClient.getEpisodesByRange(range = episodeRange)

        return if (!response.isSuccessful || response.isFailed) emptyList() else response.body
    }

    suspend fun getCharactersByPage(pageIndex: Int): CharactersPageResponse? {
        val response = NetworkLayer.apiClient.getCharactersByPage(pageIndex)

        return if (!response.isSuccessful || response.isFailed) null else response.body
    }
}