package online.zhenhong.rickandmorty.charcters

import android.util.Log
import online.zhenhong.rickandmorty.network.NetworkLayer

class CharactersRepository {
    suspend fun getCharactersPage(pageIndex: Int): CharactersPageResponse? {
        val response = NetworkLayer.apiClient.getCharactersPage(pageIndex)

        if (response.isSuccessful || !response.isFailed) return response.body

        response.exception?.let { Log.e("getCharacterById", it.localizedMessage) }

        return null
    }
}