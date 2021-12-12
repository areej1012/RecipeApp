package com.example.recipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    var retrofit : Retrofit? = null

    fun getRecipe() :  Retrofit?{
        retrofit = Retrofit.Builder()
            .baseUrl("https://dojo-recipes.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}