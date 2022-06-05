package online.zhenhong.rickandmorty.repository

import online.zhenhong.rickandmorty.domain.mappers.CharacterMapper
import online.zhenhong.rickandmorty.domain.models.Character
import online.zhenhong.rickandmorty.network.NetworkLayer
import online.zhenhong.rickandmorty.network.models.CharacterResponse
import online.zhenhong.rickandmorty.network.models.CharactersPageResponse
import online.zhenhong.rickandmorty.network.models.EpisodeResponse

class CharacterRepository {

    suspend fun getCharacterById(characterId: Int): Character? {
        val response = NetworkLayer.apiClient.getCharacterById(characterId)

        val networkEpisodes = getEpisodesFromCharacterResponse(response.body)

        return if (!response.isSuccessful || response.isFailed) null else CharacterMapper.buildFrom(
            response.body,
            networkEpisodes
        )
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