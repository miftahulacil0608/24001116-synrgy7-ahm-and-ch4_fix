package com.example.recyclerviewwithnavigationcomponent.data.dataSource.local

import androidx.lifecycle.LiveData
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.dao.LeagueDao
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueEntity
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueWithTeamsList
import com.example.recyclerviewwithnavigationcomponent.data.repository.LocalLeagueDataSource

class LocalLeagueImpl(private val leagueDao: LeagueDao) : LocalLeagueDataSource {
    override suspend fun updateDataLeague(dataLeague: LeagueEntity) {
        leagueDao.updateDataLeague(dataLeague)
    }

    override fun getAllDataLeague(): LiveData<List<LeagueWithTeamsList>> {
        return leagueDao.getAllDataLeague()
    }

    override fun getAllDataFavorite(): LiveData<List<LeagueWithTeamsList>> {
        return leagueDao.getAllDataFavoriteLeague()
    }

    override suspend fun delete() {
        leagueDao.deleteAllData()
    }

    override suspend fun isFavorite(title: String): Boolean {
        return leagueDao.isFavorite(title)
    }
}