package com.njm.infamous.data.api

import com.njm.infamous.BuildConfig
import com.njm.infamous.domain.entity.GenreList
import com.njm.infamous.domain.entity.SeriesList
import com.njm.infamous.presentation.utils.Resource
import retrofit2.http.GET
import retrofit2.http.Path

interface SerieApiService {

    @GET(BuildConfig.UrlBase+"popular?"+"api_key="+BuildConfig.ApiKey+"&language=en-US&page=1")
    suspend fun getPopularsSeries(): SeriesList

    @GET(BuildConfig.UrlBase+"{tv_id}/recommendations?"+"api_key="+BuildConfig.ApiKey+"&language=en-US&page=1")
    suspend fun getRecommendationSeries(@Path("tv_id") id: Int): SeriesList

    @GET(BuildConfig.UrlGenres+"genre/tv/list?"+"api_key="+BuildConfig.ApiKey)
    suspend fun getGenres(): GenreList

}