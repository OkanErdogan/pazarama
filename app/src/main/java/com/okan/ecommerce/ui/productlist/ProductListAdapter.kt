package com.okan.ecommerce.ui.productlist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.okan.ecommerce.R
import com.okan.ecommerce.data.BaseApplication
import com.okan.ecommerce.databinding.ItemProductlistBinding
import com.okan.ecommerce.domain.models.ProductItem
import com.okan.ecommerce.ui.detail.ProductItemDetailActivity
import com.okan.ecommerce.util.SharedPrefUtil

class ProductListAdapter(var context: Context?) :
    RecyclerView.Adapter<ProductListHolder>() {
    private var productItemList = ArrayList<ProductItem>()
    var favoriteStateLiveData = MutableLiveData<ProductItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListHolder {
        var itemProductlistBinding: ItemProductlistBinding =
            ItemProductlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductListHolder(context, itemProductlistBinding,favoriteStateLiveData)
    }

    override fun onBindViewHolder(productListHolder: ProductListHolder, position: Int) {
        productListHolder.bindProductListItem(productItemList.get(position))
    }

    override fun getItemCount(): Int {
        return productItemList.size
    }

    @JvmName("setProductItemList1")
    fun setProductItemList(productItemList: ArrayList<ProductItem>) {
        this.productItemList.clear()
        for (productItem: ProductItem in productItemList) {
            productItem.isFavorite =
                SharedPrefUtil.getFavorite(BaseApplication.getAppInstance().applicationContext)
                    ?.contains(productItem.name)!!
            this.productItemList.add(productItem)
        }
        notifyDataSetChanged()
    }

    fun setFavoriteProductItemList(productItemList: ArrayList<ProductItem>) {
        this.productItemList.clear()
        for (productItem: ProductItem in productItemList) {
            productItem.isFavorite =
                SharedPrefUtil.getFavorite(BaseApplication.getAppInstance().applicationContext)
                    ?.contains(productItem.name)!!
            this.productItemList.add(productItem)
        }

        this.productItemList = filterFavorites(this.productItemList)
        notifyDataSetChanged()
    }

    private fun filterFavorites(productItemList : ArrayList<ProductItem>): ArrayList<ProductItem> {
        return productItemList.filter {
            it.isFavorite
        } as ArrayList<ProductItem>
    }
}


class ProductListHolder(var context: Context?, var itemProductlistBinding: ItemProductlistBinding,var favoriteStateLiveData:MutableLiveData<ProductItem>) :
    RecyclerView.ViewHolder(itemProductlistBinding.root) {
    fun bindProductListItem(productItem: ProductItem) {
        itemProductlistBinding.productName.setText(productItem.name)
        itemProductlistBinding.price.setText(productItem.price.toString() + " " + productItem.currency)

        updateFavoriteImage(productItem, itemProductlistBinding.favorite)

        itemProductlistBinding.favorite.setOnClickListener {

            productItem.isFavorite = !productItem.isFavorite

            updateFavoriteImage(productItem, itemProductlistBinding.favorite)

            favoriteStateLiveData.postValue(productItem)
        }

        context?.let {
            Glide.with(it).load(productItem.imageId)
                .circleCrop().into(itemProductlistBinding.productImage)
        }

        itemProductlistBinding.productItemWrapper.setOnClickListener {
            var intent = Intent(
                it.context,
                ProductItemDetailActivity::class.java
            )
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("PRODUCT_ITEM", productItem)
            it.context.startActivity(
                intent
            )
        }
    }

    private fun updateFavoriteImage(productItem: ProductItem, favorite: LinearLayout) {
        if (productItem.isFavorite) {
            favorite.setBackgroundResource(R.drawable.filled_heart)
            context?.let { SharedPrefUtil.addFavorite(it, productItem.name) }
        } else {
            favorite.setBackgroundResource(R.drawable.empty_heart)
            context?.let { SharedPrefUtil.remoteFavorite(it, productItem.name) }
        }
    }
}