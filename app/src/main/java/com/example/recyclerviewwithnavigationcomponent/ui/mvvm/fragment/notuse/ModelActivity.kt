package com.example.recyclerviewwithnavigationcomponent.ui.mvvm.fragment.notuse

import android.content.res.Resources
import com.example.recyclerviewwithnavigationcomponent.data.model.Helper
import com.example.recyclerviewwithnavigationcomponent.R
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueEntity
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.TeamsEntity
import com.example.recyclerviewwithnavigationcomponent.domain.LeagueRepository

//Model
class ModelActivity(private val resources: Resources, private val leagueRepository: LeagueRepository) {

    private fun addListTeams():ArrayList<ArrayList<TeamsEntity>>{
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

        return arrayListOf(
            listTeamPremierLeague,
            listBundesliga,
            listSerieA,
            listLaLigaSantander,
            listEredivisie
        )
    }

    /*fun addListLeagueGroup(): List<LeagueEntity> {
        return Helper.addListLeagueGroup(
            leagueRepository,
            resources,
            R.array.data_image_league_group,
            R.array.data_league_group,
        )
    }*/
}