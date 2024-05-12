package com.example.recyclerviewwithnavigationcomponent.data.dataSource.remote

import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.dao.LeagueDao
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.dao.TeamsDao
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueEntity
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.TeamsEntity
import com.example.recyclerviewwithnavigationcomponent.data.repository.RemoteLeagueDataSource

class RemoteLeagueImpl(private val leagueDao : LeagueDao,private val teamsDao: TeamsDao):RemoteLeagueDataSource {
    override suspend fun insertLeagueData(listDataLeague: List<LeagueEntity>) {
        leagueDao.insertLeagueData(listDataLeague)
    }

    override suspend fun insertTeamsData(listDataTeams: List<TeamsEntity>) {
        teamsDao.insertDataTeam(listDataTeams)
    }

    override suspend fun deleteTeams() {
        teamsDao.deleteDataTeams()
    }


}