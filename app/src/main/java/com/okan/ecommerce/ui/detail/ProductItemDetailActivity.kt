package com.okan.ecommerce.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.okan.ecommerce.R
import com.okan.ecommerce.base.BaseActivity
import com.okan.ecommerce.data.BaseApplication
import com.okan.ecommerce.databinding.ActivityProgramItemDetailBinding
import com.okan.ecommerce.domain.models.ProductItem
import com.okan.ecommerce.util.SharedPrefUtil
import com.okan.ecommerce.viewmodel.ProductItemDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductItemDetailActivity : BaseActivity<ProductItemDetailViewModel>() {
    private val productItemDetailViewModel: ProductItemDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var productItem = intent.getSerializableExtra("PRODUCT_ITEM") as ProductItem
        var productImage = baseContentView.findViewById<ImageView>(R.id.productImage)
        Glide.with(BaseApplication.getAppInstance().baseContext).load(productItem.imageId).into(productImage)

        var productName = baseContentView.findViewById<TextView>(R.id.productName)
        productName.setText(productItem.name)

        var price = baseContentView.findViewById<TextView>(R.id.price)
        price.setText(productItem.price.toString()+" "+productItem.currency)

        var favorite = baseContentView.findViewById<LinearLayout>(R.id.favorite)

        updateFavoriteImage(productItem,favorite)

        favorite.setOnClickListener {
            productItem.isFavorite = !productItem.isFavorite
            updateFavoriteImage(productItem,favorite)
        }
    }

    private fun updateFavoriteImage(productItem : ProductItem, favorite:LinearLayout){
        if (productItem.isFavorite) {
            favorite.setBackgroundResource(R.drawable.filled_heart)
            SharedPrefUtil.addFavorite(this,productItem.name)
        }
        else {
            favorite.setBackgroundResource(R.drawable.empty_heart)
            SharedPrefUtil.remoteFavorite(this,productItem.name)
        }
    }

    override fun createViewBinding(layoutInflater: LayoutInflater): ViewBinding {
        return ActivityProgramItemDetailBinding.inflate(layoutInflater)

    }

    override fun createViewModel(): ProductItemDetailViewModel {
        return productItemDetailViewModel
    }
}