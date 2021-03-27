package com.njm.infamous.domain.useCase

import com.njm.infamous.data.repositoryImpl.SeriesListRepositoryImpl
import com.njm.infamous.domain.entity.Result
import com.njm.infamous.domain.entity.SeriesList

class GetRecommendationSeriesUseCase (private val seriesListRepositoryImpl: SeriesListRepositoryImpl) {

    suspend fun getRecommendationSeries(idSerie: Int): List<Result> {
        return seriesListRepositoryImpl.getRecommendationSeries(idSerie)
    }
}