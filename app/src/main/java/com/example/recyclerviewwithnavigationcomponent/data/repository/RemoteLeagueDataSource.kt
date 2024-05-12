package com.example.recyclerviewwithnavigationcomponent.data.repository

import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueEntity
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.TeamsEntity

interface RemoteLeagueDataSource {
    suspend fun insertLeagueData(listDataLeague : List<LeagueEntity>)
    suspend fun insertTeamsData(listDataTeams : List<TeamsEntity>)
    suspend fun deleteTeams()
}