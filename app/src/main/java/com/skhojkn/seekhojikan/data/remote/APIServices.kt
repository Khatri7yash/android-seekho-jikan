package com.skhojkn.seekhojikan.data.remote

import com.skhojkn.seekhojikan.domain.model.AnimeDetails
import com.skhojkn.seekhojikan.domain.model.AnimeListModel
import com.skhojkn.seekhojikan.domain.model.BaseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface APIServices {

    @GET(ApiURL.VERSION_V4.plus(ApiURL.TOP_ANIME_ENDPOINT))
    suspend fun getTopAnime(): AnimeListModel

    @GET(ApiURL.VERSION_V4.plus(ApiURL.ANIME_DETAILS_ENDPOINT))
    suspend fun fetchAnimeDetails(
        @Path("anime_id") animeId: Int
    ): AnimeDetails

}