package com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.dao.LeagueDao
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.dao.TeamsDao
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.LeagueEntity
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.TeamsEntity

@Database(entities = [LeagueEntity::class,TeamsEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun leagueDao() : LeagueDao
    abstract fun teamsDao():TeamsDao
}