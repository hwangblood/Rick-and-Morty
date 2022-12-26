package com.hwangblood.rickandmorty

import  com.hwangblood.rickandmorty.domain.models.Character

object RickAndMortyCache {

    val characterMap = mutableMapOf<Int, Character>()
}