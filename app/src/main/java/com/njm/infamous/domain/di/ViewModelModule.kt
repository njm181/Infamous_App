package com.njm.infamous.domain.di

import com.njm.infamous.presentation.viewModel.SerieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SerieViewModel(get(), get(), get()) }
}