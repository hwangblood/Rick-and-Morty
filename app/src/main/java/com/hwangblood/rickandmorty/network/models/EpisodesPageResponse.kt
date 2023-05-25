package com.hwangblood.rickandmorty.network.models

import com.squareup.moshi.Json

class EpisodesPageResponse(
    @Json(name = "info")
    val info: PageInfo = PageInfo(),
    @Json(name = "results")
    val results: List<EpisodeResponse> = emptyList()
) {
    val nextPageIndex: Int?
        get() =
            info.next?.split("page=")?.get(1)?.toInt()

}
