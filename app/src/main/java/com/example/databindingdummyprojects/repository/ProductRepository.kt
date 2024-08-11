package com.example.databindingdummyprojects.repository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.databindingdummyprojects.utils.NetworkResult
import com.example.databindingdummyprojects.api.ProductApi
import com.example.databindingdummyprojects.models.ProductApiResponse
import com.example.databindingdummyprojects.models.ProductApiResponseItem
import com.example.databindingdummyprojects.utils.safeApiCall
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productApi: ProductApi){

    private val _allProducts = MutableLiveData<NetworkResult<ProductApiResponse>?>()
    val allProducts :LiveData<NetworkResult<ProductApiResponse>?> get()= _allProducts



    private val _productDetails = MutableLiveData<NetworkResult<ProductApiResponseItem>?>()
    val productDetails: LiveData<NetworkResult<ProductApiResponseItem>?> = _productDetails

    suspend fun  getMovieList(){
        _allProducts.postValue(NetworkResult.Loading())
        val result = safeApiCall {productApi.getAllProducts()}
        _allProducts.postValue(result)
    }


    suspend fun getProductDetails(productId:String){
        _productDetails.postValue(NetworkResult.Loading())
        val result = safeApiCall { productApi.getMovieDetails(productId.toString()) }
        _productDetails.postValue(result)
    }







}