package com.example.recyclerviewwithnavigationcomponent.data

import androidx.lifecycle.LiveData
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueEntity
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueWithTeamsList
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.TeamsEntity
import com.example.recyclerviewwithnavigationcomponent.data.repository.LocalLeagueDataSource
import com.example.recyclerviewwithnavigationcomponent.data.repository.RemoteLeagueDataSource
import com.example.recyclerviewwithnavigationcomponent.domain.LeagueRepository

class LeagueRepositoryImpl(private val remoteLeagueDataSource: RemoteLeagueDataSource, private val localLeagueDataSource: LocalLeagueDataSource) : LeagueRepository {
    override suspend fun insertLeagueData(leagueData: List<LeagueEntity>) {
        remoteLeagueDataSource.insertLeagueData(leagueData)
    }

    override suspend fun insertTeamsData(teamsData: List<TeamsEntity>) {
        remoteLeagueDataSource.insertTeamsData(teamsData)
    }

    override suspend fun updateLeagueData(leagueData: LeagueEntity) {
        localLeagueDataSource.updateDataLeague(leagueData)
    }

    override fun getAllDataLeague(): LiveData<List<LeagueWithTeamsList>> {
        return localLeagueDataSource.getAllDataLeague()
    }

    override fun getAllDataFavorite(): LiveData<List<LeagueWithTeamsList>> {
        return localLeagueDataSource.getAllDataFavorite()
    }

    override suspend fun delete() {
        localLeagueDataSource.delete()
    }

    override suspend fun isFavorite(title: String):Boolean {
        return localLeagueDataSource.isFavorite(title)
    }

    override suspend fun deleteTeams() {
        remoteLeagueDataSource.deleteTeams()
    }
}