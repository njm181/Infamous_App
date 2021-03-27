package com.njm.infamous.presentation.viewModel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import androidx.lifecycle.*
import com.njm.infamous.domain.entity.GenreList
import com.njm.infamous.domain.entity.Result
import com.njm.infamous.domain.useCase.GetGenreListUseCase
import com.njm.infamous.domain.useCase.GetPopularsSeriesUseCase
import com.njm.infamous.domain.useCase.GetRecommendationSeriesUseCase
import com.njm.infamous.presentation.utils.Resource
import kotlinx.coroutines.*
import androidx.lifecycle.MutableLiveData
import com.njm.infamous.presentation.utils.PresentationUtils


class SerieViewModel (
    private val getPopularsSeriesUseCase: GetPopularsSeriesUseCase,
    private val getRecommendationSeriesUseCase: GetRecommendationSeriesUseCase,
    private val getGenreListUseCase: GetGenreListUseCase
) : ViewModel(){

      val getPopularSeries = liveData<Resource<List<Result>>>(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            when(PresentationUtils.isConnected){
                true -> {
                    emit(Resource.Success(getPopularsSeriesUseCase.getPopularSeries()))
                }
                else ->{
                    emit(Resource.Success(getPopularsSeriesUseCase.getPopularSeriesDb()))
                }
            }
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

    val getGenreList = liveData<Resource<GenreList>>(Dispatchers.IO) {
        emit(Resource.Loading())
        try{
            emit(Resource.Success(getGenreListUseCase.getGenreList()))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

    private var idSerie: MutableLiveData<Int>? = MutableLiveData()

    fun setIdSerie(id: Int) {
        idSerie!!.value = id
    }

    val getRecommendationsSeries = liveData<Resource<List<Result>>>(Dispatchers.IO) {
        emit(Resource.Loading())
        try{
            emit(Resource.Success(getRecommendationSeriesUseCase.getRecommendationSeries(idSerie?.value!!)))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
}