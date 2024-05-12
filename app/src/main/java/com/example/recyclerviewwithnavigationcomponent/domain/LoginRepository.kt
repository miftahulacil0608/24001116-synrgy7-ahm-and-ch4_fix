package com.example.recyclerviewwithnavigationcomponent.domain

interface LoginRepository {
    suspend fun login(email:String,password:String):String
    fun saveToken(dataToken:String)
    fun loadToken():String?
    fun clearToken()
}