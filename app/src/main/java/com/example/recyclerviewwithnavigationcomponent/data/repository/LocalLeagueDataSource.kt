package com.example.recyclerviewwithnavigationcomponent.data.repository

import androidx.lifecycle.LiveData
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueEntity
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueWithTeamsList

interface LocalLeagueDataSource {
    suspend fun updateDataLeague(dataLeague: LeagueEntity)
    fun getAllDataLeague():LiveData<List<LeagueWithTeamsList>>
    fun getAllDataFavorite():LiveData<List<LeagueWithTeamsList>>
    suspend fun delete()
    suspend fun isFavorite(title:String):Boolean
}