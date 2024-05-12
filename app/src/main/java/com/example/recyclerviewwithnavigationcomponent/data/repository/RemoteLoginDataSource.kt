package com.example.recyclerviewwithnavigationcomponent.data.repository

interface RemoteLoginDataSource {
    suspend fun login(email:String, password:String):String
}