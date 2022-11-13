package com.okan.ecommerce.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.viewbinding.ViewBinding
import com.okan.ecommerce.R
import com.okan.ecommerce.base.BaseActivity
import com.okan.ecommerce.databinding.ActivityLoginBinding
import com.okan.ecommerce.ui.productlist.ProductListActivity
import com.okan.ecommerce.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<LoginViewModel>() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var userName = baseContentView.findViewById<EditText>(R.id.username)
        var password = baseContentView.findViewById<EditText>(R.id.password)
        var login = baseContentView.findViewById<Button>(R.id.login)

        login.setOnClickListener {
            loginViewModel.login(userName.text.toString(), password.text.toString()).observe(
                this
            ) {
                when (it.success) {
                    true -> {
                        Toast.makeText(this, "Login başarılı", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@LoginActivity, ProductListActivity::class.java))
                        finish()
                    }

                    false -> {
                        Toast.makeText(this, "Login başarısız.", Toast.LENGTH_LONG).show()
                        userName.text.clear()
                        password.text.clear()
                    }
                }
            }
        }
    }

    override fun createViewBinding(layoutInflater: LayoutInflater): ViewBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun createViewModel(): LoginViewModel {
        return loginViewModel
    }
}