package com.example.recyclerviewwithnavigationcomponent.ui.splashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerviewwithnavigationcomponent.databinding.ActivitySplashScreenBinding
import com.example.recyclerviewwithnavigationcomponent.ui.mvvm.MainActivity
import com.example.recyclerviewwithnavigationcomponent.ui.login.LoginActivity

class SplashScreenActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivitySplashScreenBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<SplashScreenViewModel> { SplashScreenViewModel.provideFactory(this,this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({
            if (viewModel.loadToken().isNullOrEmpty()){
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        },3_000)
    }
}