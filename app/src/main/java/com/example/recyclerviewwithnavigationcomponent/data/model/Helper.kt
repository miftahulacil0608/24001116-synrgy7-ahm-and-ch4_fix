package com.example.recyclerviewwithnavigationcomponent.data.model

import android.content.res.Resources
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueEntity
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.TeamsEntity
import com.example.recyclerviewwithnavigationcomponent.domain.LeagueRepository

object Helper {
    /*fun addListLeagueGroup(
        leagueRepository: LeagueRepository,
        resources: Resources,
        resImg: Int,
        resTitle: Int,
    ): List<LeagueEntity> {
        val title = resources.getStringArray(resTitle)
        val img = resources.obtainTypedArray(resImg)
        val listLeague = ArrayList<LeagueEntity>()
        for (i in title.indices) {
            val isFavorite = leagueRepository.isFavorite(title[i])
            listLeague.add(
                LeagueEntity(
                    titleLeague = title[i],
                    image = img.getResourceId(i, -1),
                    favorite = isFavorite
                )
            )
        }
        leagueRepository.delete()
        leagueRepository.insertLeagueData(listLeague)
        return listLeague
    }*/

    fun addListTeams(
        resources: Resources,
        leagueName: String,
        resImg: Int,
        resTitle: Int,
        resCity: Int
    ): ArrayList<TeamsEntity> {
        val imgTeam = resources.obtainTypedArray(resImg)
        val titleTeam = resources.getStringArray(resTitle)
        val cityTeam = resources.getStringArray(resCity)
        val listTeams = ArrayList<TeamsEntity>()
        for (i in titleTeam.indices) {
            listTeams.add(
                TeamsEntity(
                    titleLeague = leagueName,
                    imgTeams = imgTeam.getResourceId(i, -1),
                    titleTeams = titleTeam[i],
                    cityTeam = cityTeam[i]
                )
            )
        }
        return listTeams
    }
}