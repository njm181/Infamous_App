package com.njm.infamous.domain.useCase

import com.njm.infamous.data.repositoryImpl.SeriesListRepositoryImpl
import com.njm.infamous.domain.entity.GenreList
import com.njm.infamous.domain.entity.Result

class GetGenreListUseCase(private val seriesListRepositoryImpl: SeriesListRepositoryImpl) {

    suspend fun getGenreList(): GenreList {
        return seriesListRepositoryImpl.getGenreList()
    }
}