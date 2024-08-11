package com.example.databindingdummyprojects.repository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.databindingdummyprojects.utils.NetworkResult
import com.example.databindingdummyprojects.api.ProductApi
import com.example.databindingdummyprojects.models.ProductApiResponse
import com.example.databindingdummyprojects.utils.safeApiCall
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productApi: ProductApi){

    private val _allProducts = MutableLiveData<NetworkResult<ProductApiResponse>?>()
    val allProducts :LiveData<NetworkResult<ProductApiResponse>?> get()= _allProducts



//    private val _movieDetails = MutableLiveData<NetworkResult<MovieDetailResponseModel>?>()
//    val movieDetails: LiveData<NetworkResult<MovieDetailResponseModel>?> = _movieDetails

    suspend fun  getMovieList(){
        _allProducts.postValue(NetworkResult.Loading())
        val result = safeApiCall {productApi.getAllProducts()}
        _allProducts.postValue(result)
    }

//    suspend fun getMovieDetails(imdbId:String){
//        _movieDetails.postValue(NetworkResult.Loading())
//        val result = com.example.databindingdummyprojects.utils.safeApiCall { movieApi.getMovieDetails(imdbId,Constants.API_KEY) }
//        _movieDetails.postValue(result)
//    }






}