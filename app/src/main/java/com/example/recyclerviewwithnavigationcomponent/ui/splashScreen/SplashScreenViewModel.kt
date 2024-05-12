package com.example.recyclerviewwithnavigationcomponent.ui.splashScreen

import android.content.Context
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.recyclerviewwithnavigationcomponent.data.LoginDataSource
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.LocalLoginImpl
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.remote.RemoteLoginImpl
import com.example.recyclerviewwithnavigationcomponent.data.model.SharedPreferences
import com.example.recyclerviewwithnavigationcomponent.data.repository.LocalLoginDataSource
import com.example.recyclerviewwithnavigationcomponent.data.repository.RemoteLoginDataSource
import com.example.recyclerviewwithnavigationcomponent.domain.LoginRepository

class SplashScreenViewModel(private val loginRepository: LoginRepository):ViewModel() {

    companion object {
        fun provideFactory(
            owner: SavedStateRegistryOwner,
            context: Context
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, null) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {

                    val remoteLoginDataSource: RemoteLoginDataSource = RemoteLoginImpl()
                    val localLoginDataSource: LocalLoginDataSource = LocalLoginImpl(
                        SharedPreferences().getSharedPreferences(context.applicationContext)
                    )
                    val loginRepository: LoginRepository = LoginDataSource(
                        remoteLoginDataSource = remoteLoginDataSource,
                        localLoginDataSource = localLoginDataSource,
                    )
                    return SplashScreenViewModel(loginRepository = loginRepository) as T
                }
            }
    }

    fun loadToken():String?{
        return loginRepository.loadToken()
    }
}