package com.hwangblood.rickandmorty.charcter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.hwangblood.rickandmorty.Constants
import com.hwangblood.rickandmorty.network.models.CharacterResponse
import com.hwangblood.rickandmorty.repository.CharacterRepository

class CharactersViewModel : ViewModel() {

    private val repository = CharacterRepository()

    private val pagedListConfig: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(Constants.PAGE_SIZE)
        .setPrefetchDistance(Constants.PREFETCH_DISTANCE)
        .build()

    private val dataSourceFactory = CharactersDataSourceFactory(viewModelScope, repository)

    val charactersPagedListLiveData: LiveData<PagedList<CharacterResponse>> =
        LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()
}