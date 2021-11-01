package com.example.recipeapp.api

import com.example.recipeapp.model.Recipe
import com.example.recipeapp.model.RecipeList
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {
    @GET("/recipes/")
    fun getRecipes(): Call<ArrayList<RecipeList?>>

    @POST("/recipes/")
    fun addRecipe(@Body userdata: Recipe ): Call<Recipe>
}