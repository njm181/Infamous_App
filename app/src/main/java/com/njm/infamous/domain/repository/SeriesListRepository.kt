package com.njm.infamous.domain.repository

import com.njm.infamous.domain.entity.GenreList
import com.njm.infamous.domain.entity.Result

interface SeriesListRepository {
    suspend fun getPopularsSeries(): List<Result>

    suspend fun getRecommendationSeries(idSerie: Int): List<Result>

    suspend fun getGenreList(): GenreList

    suspend fun getDbAllPopularSeries(): List<Result>

}