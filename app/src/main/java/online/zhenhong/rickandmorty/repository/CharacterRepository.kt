package online.zhenhong.rickandmorty.repository

import android.util.Log
import online.zhenhong.rickandmorty.charcter.CharacterResponse
import online.zhenhong.rickandmorty.charcter.CharactersPageResponse
import online.zhenhong.rickandmorty.network.NetworkLayer

class CharacterRepository {

    suspend fun getCharacterById(characterId: Int): CharacterResponse? {
        val response = NetworkLayer.apiClient.getCharacterById(characterId)

        if (response.isSuccessful) return response.body

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