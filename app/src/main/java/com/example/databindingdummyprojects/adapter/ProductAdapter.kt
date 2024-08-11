package com.example.databindingdummyprojects.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.databindingdummyprojects.databinding.LayoutProductBinding
import com.example.databindingdummyprojects.models.ProductApiResponseItem

class ProductAdapter(private val onProductClicked: (ProductApiResponseItem) -> Unit) :
    ListAdapter<ProductApiResponseItem, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            LayoutProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
    }

    inner class ProductViewHolder(private val binding: LayoutProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductApiResponseItem) {
            binding.product = product

//            binding.clickListener = View.OnClickListener{
//
//            }

            binding.fullLayout.setOnClickListener(){
                onProductClicked(product)
            }



            binding.executePendingBindings()


        }
    }

    class ProductDiffCallback : DiffUtil.ItemCallback<ProductApiResponseItem>() {
        override fun areItemsTheSame(
            oldItem: ProductApiResponseItem,
            newItem: ProductApiResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProductApiResponseItem,
            newItem: ProductApiResponseItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}