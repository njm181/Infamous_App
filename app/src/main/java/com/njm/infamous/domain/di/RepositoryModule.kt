package com.njm.infamous.domain.di

import com.njm.infamous.data.repositoryImpl.SeriesListRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        SeriesListRepositoryImpl(get(), get()) //el get() ya identifica que debe pasar una instancia de SerieApiService que esta en NetworkModule
    }
}