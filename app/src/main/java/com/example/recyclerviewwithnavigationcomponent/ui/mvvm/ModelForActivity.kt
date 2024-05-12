package com.example.recyclerviewwithnavigationcomponent.ui.mvvm

import android.annotation.SuppressLint
import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.recyclerviewwithnavigationcomponent.R
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueEntity
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueWithTeamsList
import com.example.recyclerviewwithnavigationcomponent.data.model.Helper
import com.example.recyclerviewwithnavigationcomponent.domain.LeagueRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ModelForActivity(
    private val leagueRepository: LeagueRepository,
    private val resources: Resources
) {

    @SuppressLint("Recycle")
    fun setDataLeague(): LiveData<List<LeagueWithTeamsList>> = liveData{
        try {
            val title = resources.getStringArray(R.array.data_league_group)
            val img = resources.obtainTypedArray(R.array.data_image_league_group)
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
            addListTeams()

            val getAllLeagueData = leagueRepository.getAllDataLeague()
            emitSource(getAllLeagueData)
        } catch (throwable: Throwable) {
            throw IllegalAccessException(throwable.message)
        }
    }

    private suspend fun addListTeams() {
        withContext(Dispatchers.IO) {
            try {
                val listTeamPremierLeague = Helper.addListTeams(
                    resources = resources,
                    leagueName = "Premier League",
                    resImg = R.array.list_image_premier_league,
                    resTitle = R.array.list_premier_league,
                    resCity = R.array.list_city_team_premier_league
                )
                val listBundesliga = Helper.addListTeams(
                    resources,
                    leagueName = "Bundesliga",
                    R.array.list_image_bundesliga,
                    R.array.list_bundesliga,
                    R.array.list_city_team_bundesliga
                )
                val listSerieA = Helper.addListTeams(
                    resources,
                    leagueName = "Serie A",
                    R.array.list_image_serie_a,
                    R.array.list_serie_a,
                    R.array.list_city_team_serie_a
                )
                val listLaLigaSantander = Helper.addListTeams(
                    resources,
                    leagueName = "La Liga Santander",
                    R.array.data_image_league_group,
                    R.array.data_league_group,
                    R.array.data_league_group
                )
                val listEredivisie = Helper.addListTeams(
                    resources,
                    leagueName = "Eredivisie",
                    R.array.data_image_league_group,
                    R.array.data_league_group,
                    R.array.data_league_group
                )

                val allListTeams = arrayListOf(
                    listTeamPremierLeague,
                    listBundesliga,
                    listSerieA,
                    listLaLigaSantander,
                    listEredivisie
                )
                leagueRepository.deleteTeams()
                allListTeams.forEach {
                    leagueRepository.insertTeamsData(it)
                }
            }catch (throwable: Throwable) {
                throw IllegalAccessException(throwable.message)
            }

        }
    }

    fun getFavorite(): LiveData<List<LeagueWithTeamsList>> {
        return leagueRepository.getAllDataFavorite()
    }

    suspend fun setFavorite(leagueData: LeagueEntity, isFavorite: Boolean) {
        leagueData.favorite = isFavorite
        leagueRepository.updateLeagueData(leagueData)
    }

}