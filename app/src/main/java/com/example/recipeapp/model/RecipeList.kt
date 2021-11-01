package com.example.recipeapp.model

import com.google.gson.annotations.SerializedName

class RecipeList {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("author")
    var author: String? = null

    @SerializedName("ingredients")
    var ingredients: String? = null

    @SerializedName("instructions")
    var instructions: String? = null
}