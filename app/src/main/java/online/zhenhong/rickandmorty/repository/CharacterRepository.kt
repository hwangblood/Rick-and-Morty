package online.zhenhong.rickandmorty.repository

import android.util.Log
import online.zhenhong.rickandmorty.domain.mappers.CharacterMapper
import online.zhenhong.rickandmorty.domain.models.Character
import online.zhenhong.rickandmorty.network.NetworkLayer
import online.zhenhong.rickandmorty.network.models.CharactersPageResponse

class CharacterRepository {

    suspend fun getCharacterById(characterId: Int): Character? {
        val response = NetworkLayer.apiClient.getCharacterById(characterId)

        if (response.isSuccessful) return CharacterMapper.buildFrom(response.body)

        response.exception?.let { Log.e("getCharacterById", it.localizedMessage) }

        return null
    }

    suspend fun getCharactersByPage(pageIndex: Int): CharactersPageResponse? {
        val response = NetworkLayer.apiClient.getCharactersByPage(pageIndex)

        if (response.isSuccessful || !response.isFailed) return response.body

        response.exception?.let { Log.e("getCharactersByPage", it.localizedMessage) }

        return null
    }
}