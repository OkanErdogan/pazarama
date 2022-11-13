package com.okan.ecommerce.ui.productlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.okan.ecommerce.R
import com.okan.ecommerce.base.BaseActivity
import com.okan.ecommerce.databinding.ActivityProductlistBinding
import com.okan.ecommerce.domain.models.ProductItem
import com.okan.ecommerce.ui.favorites.FavoritesActivity
import com.okan.ecommerce.viewmodel.ProductListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListActivity : BaseActivity<ProductListViewModel>() {
    private val productListViewModel: ProductListViewModel by viewModels()
    private lateinit var productListAdapter: ProductListAdapter
    private var productItemList = ArrayList<ProductItem>()
    private lateinit var searchEditText: EditText
    private lateinit var productListRecyclerView: RecyclerView
    private lateinit var emptyView: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchEditText = baseContentView.findViewById(R.id.searchEditText)
        productListRecyclerView = baseContentView.findViewById(R.id.productListRecyclerView)
        this.productListAdapter = ProductListAdapter(baseContext)
        productListRecyclerView.setLayoutManager(LinearLayoutManager(this))
        productListRecyclerView.adapter = productListAdapter


        searchEditText.addTextChangedListener { editable ->
            productListAdapter.setProductItemList(productItemList.filter {
                it.name.contains(editable.toString(), true)
            } as ArrayList<ProductItem>)

            isEmpty(productListAdapter.itemCount==0)
        }

        var favorites = baseContentView.findViewById<ImageView>(R.id.favorites)
        emptyView = baseContentView.findViewById(R.id.emptyView)


        Glide.with(this)
            .load(R.drawable.jelly_heart)
            .into(favorites)

        favorites.setOnClickListener{
            var intent = Intent(this, FavoritesActivity::class.java)
            intent.putExtra("PRODUCT_LIST",productItemList)
            startActivity(intent)
        }

        isEmpty(productListAdapter.itemCount==0)
    }

    override fun onStart() {
        super.onStart()
        viewModel.nextPage().observe(this, Observer {
            productItemList.clear()
            productItemList.addAll(it.productItemList)
            productListAdapter.setProductItemList(productItemList)
            isEmpty(productListAdapter.itemCount==0)
        })
    }

    override fun createViewBinding(layoutInflater: LayoutInflater): ViewBinding {
        return ActivityProductlistBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): ProductListViewModel {
        return productListViewModel
    }

    override fun onBackPressed() {
        val builder1: AlertDialog.Builder =
            AlertDialog.Builder(this, androidx.appcompat.R.style.Theme_AppCompat_Dialog_Alert)
        builder1.setMessage(R.string.exit_message)
        builder1.setCancelable(true)
        builder1.setPositiveButton(R.string.yes) { dialog, id ->
            finish()
            System.exit(0)
            dialog.cancel()
        }
        builder1.setNegativeButton(R.string.no, { dialog, id -> dialog.cancel() })
        val alert11: AlertDialog = builder1.create()
        alert11.show()
    }

    private fun isEmpty(isEmpty : Boolean){
        if (isEmpty) {
            productListRecyclerView.visibility = View.GONE
            searchEditText.visibility = View.GONE
            emptyView.visibility = View.VISIBLE
        } else {
            productListRecyclerView.visibility = View.VISIBLE
            searchEditText.visibility = View.VISIBLE
            emptyView.visibility = View.GONE
        }
    }
}