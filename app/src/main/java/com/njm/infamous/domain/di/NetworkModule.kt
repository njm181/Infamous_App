package com.njm.infamous.domain.di

import com.njm.infamous.BuildConfig
import com.njm.infamous.data.api.SerieApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) } //el get() detecta que necesita pasarle una instancia de okHttpClient del arbol de dependencias por parametro
    single { provideApiService(get(), SerieApiService::class.java) }
}

fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.UrlBase)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

fun provideApiService(retrofit: Retrofit, apiService: Class<SerieApiService>) =
    createService(retrofit, apiService)

fun <T> createService(retrofit: Retrofit, serviceClassGeneric: Class<T>): T = retrofit.create(serviceClassGeneric)

