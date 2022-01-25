package com.example.composereciepeeapp.network.model.repository

import com.example.composereciepeeapp.domain.model.Recipe

interface RecipeRepository {
    suspend fun search(token: String, page: Int, query: String): List<Recipe>

    suspend fun get(token: String, id: Int): Recipe
}