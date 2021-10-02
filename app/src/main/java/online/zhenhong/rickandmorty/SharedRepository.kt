package online.zhenhong.rickandmorty

import android.util.Log
import online.zhenhong.rickandmorty.network.CharacterResponse
import online.zhenhong.rickandmorty.network.NetworkLayer

class SharedRepository {

    suspend fun getCharacterById(characterId: Int): CharacterResponse? {
        val response = NetworkLayer.apiClient.getCharacterById(characterId)

        if (response.isSuccessful) return response.body

        response.exception?.let { Log.e("getCharacterById", it.localizedMessage) }

        return null
    }
}
