package com.example.route.presentation

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getString
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.route.R
import com.example.route.databinding.ItemProductListBinding
import com.example.route.domain.models.Product

class ProductListAdapter :
    ListAdapter<Product, ProductListAdapter.ViewHolder>(CategoryDetailsListDiffCallBack()) {


    inner class ViewHolder(private val binding: ItemProductListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Product) {
            binding.apply {
                Glide.with(root.context).load(item.thumbnail).into(productImage)
                productTitle.text = item.title
                productDescription.text = item.description
                productNewPrice.text =
                    (getString(
                        root.context,
                        R.string.egp
                    ) + " " + item.discountedPrice.toString())
                productOldPrice.text =
                    (getString(root.context, R.string.egp) + " " + item.price
                        .toString())
                productOldPrice.paintFlags =
                    productOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                textViewReview.text =
                    itemView.context.getString(R.string.review_format, item.rating)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemProductListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CategoryDetailsListDiffCallBack :
    DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(
        oldItem: Product,
        newItem: Product
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Product,
        newItem: Product
    ): Boolean {
        return oldItem == newItem
    }
}
