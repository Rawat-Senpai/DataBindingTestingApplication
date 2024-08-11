package com.example.databindingdummyprojects.ui

import com.example.databindingdummyprojects.repository.ProductRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.databindingdummyprojects.utils.NetworkResult
import com.example.databindingdummyprojects.models.ProductApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ProductRepository):ViewModel() {


    fun getMovieList(){

        viewModelScope.launch {
            repository.getMovieList()
        }

    }

    val getAllProducts:LiveData<NetworkResult<ProductApiResponse>?> get() = repository.allProducts
//
//
//    fun getMovieDetails(imdbId:String){
//
//        viewModelScope.launch {
//            repository.getMovieDetails(imdbId)
//        }
//
//    }
//
//    val getMovieDetails:LiveData<NetworkResult<MovieDetailResponseModel>?> get() = repository.movieDetails


}