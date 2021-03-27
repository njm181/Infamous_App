package com.njm.infamous.data.repositoryImpl

import com.njm.infamous.data.api.SerieApiService
import com.njm.infamous.data.db.SeriesDatabase
import com.njm.infamous.domain.entity.GenreList
import com.njm.infamous.domain.entity.Result
import com.njm.infamous.domain.repository.SeriesListRepository

class SeriesListRepositoryImpl (private val serieApiService: SerieApiService, private val database: SeriesDatabase): SeriesListRepository {


    override suspend fun getPopularsSeries(): List<Result> {
        serieApiService.getPopularsSeries().let {
            database.serieDao().insertAll(it.results)
            return it.results
        }
    }

    override suspend fun getRecommendationSeries(idSerie: Int): List<Result> {
        return serieApiService.getRecommendationSeries(idSerie).results
    }

    override suspend fun getGenreList(): GenreList {
        return serieApiService.getGenres()
    }

    override suspend fun getDbAllPopularSeries(): List<Result> {
        return database.serieDao().getAll()
    }

}