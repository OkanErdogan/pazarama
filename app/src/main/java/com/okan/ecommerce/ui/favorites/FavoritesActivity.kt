package com.okan.ecommerce.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.okan.ecommerce.R
import com.okan.ecommerce.base.BaseActivity
import com.okan.ecommerce.databinding.ActivityFavoritesBinding
import com.okan.ecommerce.domain.models.ProductItem
import com.okan.ecommerce.ui.productlist.ProductListAdapter
import com.okan.ecommerce.viewmodel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesActivity : BaseActivity<FavoritesViewModel>() {
    private val favoritesViewModel: FavoritesViewModel by viewModels()
    private lateinit var productListAdapter: ProductListAdapter
    private lateinit var productItemList: ArrayList<ProductItem>

    private lateinit var searchEditText: EditText
    private lateinit var favoritesRecyclerView: RecyclerView
    private lateinit var emptyView: RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        productItemList = intent.getSerializableExtra("PRODUCT_LIST") as ArrayList<ProductItem>
        searchEditText = baseContentView.findViewById(R.id.searchEditText)
        favoritesRecyclerView = baseContentView.findViewById(R.id.favoritesRecyclerView)
        this.productListAdapter = ProductListAdapter(baseContext)
        favoritesRecyclerView.setLayoutManager(LinearLayoutManager(this))
        favoritesRecyclerView.adapter = productListAdapter


        searchEditText.addTextChangedListener { editable ->
            productListAdapter.setFavoriteProductItemList(productItemList.filter {
                it.name.contains(editable.toString(), true)
            } as ArrayList<ProductItem>)

            isEmpty(productListAdapter.itemCount==0)
        }


        productListAdapter.favoriteStateLiveData.observe(this, Observer {
            for (i in 0..productItemList.size - 1) {
                if (productItemList.get(i).name.equals(it.name)) {
                    productItemList.get(i).isFavorite = it.isFavorite
                    continue
                }
            }
            productListAdapter.setFavoriteProductItemList(productItemList)
            isEmpty(productListAdapter.itemCount==0)
        })

        emptyView = baseContentView.findViewById(R.id.emptyView)

        isEmpty(productListAdapter.itemCount==0)
    }

    override fun onResume() {
        super.onResume()
        productListAdapter.setFavoriteProductItemList(productItemList)
        isEmpty(productListAdapter.itemCount==0)
    }


    override fun createViewBinding(layoutInflater: LayoutInflater): ViewBinding {
        return ActivityFavoritesBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): FavoritesViewModel {
        return favoritesViewModel
    }

    private fun isEmpty(isEmpty : Boolean){
        if (isEmpty) {
            favoritesRecyclerView.visibility = View.GONE
            searchEditText.visibility = View.GONE
            emptyView.visibility = View.VISIBLE
        } else {
            favoritesRecyclerView.visibility = View.VISIBLE
            searchEditText.visibility = View.VISIBLE
            emptyView.visibility = View.GONE
        }
    }


}