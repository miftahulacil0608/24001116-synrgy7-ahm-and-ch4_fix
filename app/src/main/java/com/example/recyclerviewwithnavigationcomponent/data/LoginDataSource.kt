package com.example.recyclerviewwithnavigationcomponent.data

import com.example.recyclerviewwithnavigationcomponent.data.repository.LocalLoginDataSource
import com.example.recyclerviewwithnavigationcomponent.data.repository.RemoteLoginDataSource
import com.example.recyclerviewwithnavigationcomponent.domain.LoginRepository

class LoginDataSource(private val remoteLoginDataSource: RemoteLoginDataSource, private val localLoginDataSource: LocalLoginDataSource):LoginRepository {
    override suspend fun login(email: String, password: String): String {
      return  remoteLoginDataSource.login(email,password)
    }

    override fun saveToken(dataToken: String) {
        localLoginDataSource.saveToken(dataToken)
    }

    override fun loadToken(): String? {
        return localLoginDataSource.loadToken()
    }

    override fun clearToken() {
        return localLoginDataSource.clearToken()
    }
}