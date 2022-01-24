package com.example.composereciepeeapp.network.model.response

import com.example.composereciepeeapp.network.model.RecipeNetworkEntity
import com.google.gson.annotations.SerializedName

class RecipeSearchResponse {

    @SerializedName("count")
    var count: Int = 0

    @SerializedName("results")
    var recipes: List<RecipeNetworkEntity> = TODO()

}