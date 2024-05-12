package com.example.recyclerviewwithnavigationcomponent.data.dataSource.remote

import com.example.recyclerviewwithnavigationcomponent.data.model.dataClass.User
import com.example.recyclerviewwithnavigationcomponent.data.repository.RemoteLoginDataSource

class RemoteLoginImpl: RemoteLoginDataSource {
    private val dataUser = listOf(
        User("synergy7chapter4@gmail.com","synergy7chapter4"),
        User("chapter4@gmail.com","chapter4")
    )

    override suspend fun login(email: String, password: String): String {
        if (dataUser.contains(User(email,password))){
            return "tokenLogin123"
        }else{
            throw IllegalAccessException("User Empty")
        }
    }
}