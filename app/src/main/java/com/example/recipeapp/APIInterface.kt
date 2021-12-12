package com.example.recipeapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {

    @GET("recipes/")
    fun getRecipes(): Call<Recipes>

    @POST("recipes/")
    fun sendRecipes(@Body recipesItem: RecipesItem) : Call<RecipesItem>
}