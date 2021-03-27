package com.njm.infamous.domain.useCase

import com.njm.infamous.data.db.SeriesDatabase
import com.njm.infamous.data.repositoryImpl.SeriesListRepositoryImpl
import com.njm.infamous.domain.entity.Result

class GetPopularsSeriesUseCase (private val seriesListRepositoryImpl: SeriesListRepositoryImpl,
                                private val database: SeriesDatabase){

    suspend fun getPopularSeries(): List<Result> {
        return seriesListRepositoryImpl.getPopularsSeries()
    }

    suspend fun getPopularSeriesDb(): List<Result>{
        return database.serieDao().getAll()
    }

}