package com.example.recyclerviewwithnavigationcomponent.data.dataSource.local

import android.content.SharedPreferences
import com.example.recyclerviewwithnavigationcomponent.data.repository.LocalLoginDataSource

class LocalLoginImpl(private val sharedPreferences: SharedPreferences?): LocalLoginDataSource {

    companion object{
        const val KEY_TOKEN = "KEY TOKEN"
    }

    override fun saveToken(dataToken: String) {
        sharedPreferences?.edit()?.putString(KEY_TOKEN,dataToken)?.apply()
    }

    override fun loadToken(): String? {
        return sharedPreferences?.getString(KEY_TOKEN,null)
    }

    override fun clearToken() {
        sharedPreferences?.edit()?.remove(KEY_TOKEN)?.apply()
    }
}