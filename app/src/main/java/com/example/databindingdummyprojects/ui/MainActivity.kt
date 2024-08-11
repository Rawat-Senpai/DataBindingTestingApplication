package com.example.databindingdummyprojects.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databindingdummyprojects.utils.NetworkResult
import com.example.databindingdummyprojects.R
import com.example.databindingdummyprojects.adapter.ProductAdapter
import com.example.databindingdummyprojects.databinding.ActivityMainBinding
import com.example.databindingdummyprojects.models.ProductApiResponseItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel>()

//    private val   productAdapter by lazy { ProductAdapter(::onProductClicked) }
lateinit var productAdapter:ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)



        productAdapter = ProductAdapter(::onProductClicked)
        binding.recyclerView.adapter=productAdapter
        binding.recyclerView.layoutManager= LinearLayoutManager(this)

        viewModel.getMovieList()

        observeData()


    }

    private fun observeData() {

        viewModel.getAllProducts.observe(this, Observer {
            when (it) {
                is NetworkResult.Error -> {
                    Log.d("checkingData_error", it.message.toString())
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Loading -> {
                    Log.d("checkingData_Loading",it.data.toString())
                        // binding.progressBar.isVisible= true
                }

                is NetworkResult.Success -> {
                    Log.d("checkingData_success",it.data.toString())
                    productAdapter.submitList(it.data)
                }

                else -> {}
            }
        })


    }

    private fun onProductClicked(product: ProductApiResponseItem){
        val intent = Intent(this,DetailsActivity::class.java)
        intent.putExtra("id",product.id.toString())
        startActivity(intent)
    }


}