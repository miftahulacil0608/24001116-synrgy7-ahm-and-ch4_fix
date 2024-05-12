package com.example.recyclerviewwithnavigationcomponent.ui.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.example.recyclerviewwithnavigationcomponent.data.LoginDataSource
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.LocalLoginImpl
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.remote.RemoteLoginImpl
import com.example.recyclerviewwithnavigationcomponent.data.model.SharedPreferences
import com.example.recyclerviewwithnavigationcomponent.data.repository.LocalLoginDataSource
import com.example.recyclerviewwithnavigationcomponent.data.repository.RemoteLoginDataSource
import com.example.recyclerviewwithnavigationcomponent.domain.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private var _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success

    private var _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error


    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                val dataToken = loginRepository.login(email, password)
                loginRepository.saveToken(dataToken)
                _loading.value = false
                _success.value = true
            } catch (throwable: Throwable) {
                _error.value = throwable
                _success.value = false
                Log.d("Error login", "Message: ${throwable.message}")
            }
        }
    }

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
                    return LoginViewModel(loginRepository = loginRepository) as T
                }
            }
    }
}