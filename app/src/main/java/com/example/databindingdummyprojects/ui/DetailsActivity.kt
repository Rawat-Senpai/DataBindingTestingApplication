package com.example.databindingdummyprojects.ui

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
import com.example.databindingdummyprojects.R
import com.example.databindingdummyprojects.databinding.ActivityDetailsBinding
import com.example.databindingdummyprojects.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding :ActivityDetailsBinding
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_details)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val productId = intent.getStringExtra("id")
        Log.d("dataCheck",productId.toString())


        viewModel.getProductDetails(productId.toString())
        bindObservers()
    }

    private fun bindObservers() {

        viewModel.getProductDetails.observe(this, Observer {

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
                   binding.product= it.data
                }

                else -> {}
            }

        })



    }


}