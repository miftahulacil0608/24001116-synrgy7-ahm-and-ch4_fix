package com.example.recyclerviewwithnavigationcomponent.domain

import androidx.lifecycle.LiveData
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueEntity
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueWithTeamsList
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.TeamsEntity

interface LeagueRepository {
    suspend fun insertLeagueData(leagueData : List<LeagueEntity>)
    suspend fun insertTeamsData(teamsData : List<TeamsEntity>)
    suspend fun updateLeagueData(leagueData : LeagueEntity)
     fun getAllDataLeague():LiveData<List<LeagueWithTeamsList>>
    fun getAllDataFavorite():LiveData<List<LeagueWithTeamsList>>
    suspend fun delete()
    suspend fun isFavorite(title:String):Boolean
    suspend fun deleteTeams()
}