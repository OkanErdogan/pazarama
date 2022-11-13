package com.okan.ecommerce.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VM : ViewModel> : AppCompatActivity() {
    lateinit var binding: ViewBinding
    lateinit var viewModel: VM
    lateinit var baseContentView: View

    protected abstract fun createViewBinding(layoutInflater: LayoutInflater): ViewBinding

    protected abstract fun createViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
        binding = createViewBinding(LayoutInflater.from(this))
        baseContentView = binding.root
        setContentView(baseContentView)
    }
}