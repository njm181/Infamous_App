package com.njm.infamous.domain.di

import com.njm.infamous.domain.useCase.GetGenreListUseCase
import com.njm.infamous.domain.useCase.GetPopularsSeriesUseCase
import com.njm.infamous.domain.useCase.GetRecommendationSeriesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPopularsSeriesUseCase(get(), get()) }
    factory { GetRecommendationSeriesUseCase(get()) }
    factory { GetGenreListUseCase(get()) }
}