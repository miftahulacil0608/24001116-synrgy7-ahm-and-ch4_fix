package com.example.recyclerviewwithnavigationcomponent.data.model

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences {

    companion object{
        const val NAME_SHARED_PREFERENCES = "SHARED PREFERENCES"
    }
    fun getSharedPreferences(context:Context?): SharedPreferences? {
        return context?.getSharedPreferences(NAME_SHARED_PREFERENCES, Context.MODE_PRIVATE)
    }
}