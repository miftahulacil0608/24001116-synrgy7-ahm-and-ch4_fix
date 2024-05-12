package com.example.recyclerviewwithnavigationcomponent.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerviewwithnavigationcomponent.R
import com.example.recyclerviewwithnavigationcomponent.databinding.ActivityLoginBinding
import com.example.recyclerviewwithnavigationcomponent.ui.mvvm.MainActivity

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<LoginViewModel> {
        LoginViewModel.provideFactory(
            this,
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(this)

        viewModel.loading.observe(this) { loading ->
            if (loading) binding.btnFlipper.displayedChild = 0
            else binding.btnFlipper.displayedChild = 1
        }

        viewModel.success.observe(this) { success ->
            if (success) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        viewModel.error.observe(this) {
            Toast.makeText(this, "error: ${it.message}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login -> {
                if (binding.edtEmail.text.isNullOrEmpty()) binding.itlEmail.error = "Email Empty"
                if (binding.edtPassword.text.isNullOrEmpty()) binding.itlPassword.error =
                    "Password Empty"
                else {
                    binding.itlEmail.error = null
                    binding.itlPassword.error = null
                    val email = binding.edtEmail.text.toString()
                    val password = binding.edtPassword.text.toString()
                    viewModel.login(email, password)
                }
            }
        }
    }
}