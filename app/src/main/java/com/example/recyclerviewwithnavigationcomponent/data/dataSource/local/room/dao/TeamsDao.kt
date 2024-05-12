package com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recyclerviewwithnavigationcomponent.data.dataSource.local.room.entity.TeamsEntity

@Dao
interface TeamsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataTeam(listDataTeamsEntity: List<TeamsEntity>)

    @Query("DELETE FROM TeamsEntity")
    fun deleteDataTeams()
}