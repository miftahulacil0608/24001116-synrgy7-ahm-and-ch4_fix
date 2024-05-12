package com.example.recyclerviewwithnavigationcomponent.data.repository

interface LocalLoginDataSource {
    fun saveToken(dataToken:String)
    fun loadToken():String?
    fun clearToken()
}