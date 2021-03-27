package com.njm.infamous.domain.di

import android.app.Application
import androidx.room.Room
import com.njm.infamous.data.db.SeriesDatabase
import com.njm.infamous.data.entityDAO.SerieDAO
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    val DB_NAME = "series.db"

    fun provideDatabase(application: Application): SeriesDatabase {
        return Room.databaseBuilder(application, SeriesDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCountriesDao(database: SeriesDatabase): SerieDAO {
        return  database.serieDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }
}