package com.hwangblood.rickandmorty.network.models

import com.squareup.moshi.Json

data class PageInfo(
    @Json(name = "count")
    val count: Int = 0,
    @Json(name = "next")
    val next: String? = null,
    @Json(name = "pages")
    val pages: Int = 0,
    @Json(name = "prev")
    val prev: String? = null
)