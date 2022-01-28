package com.example.composereciepeeapp.presentation.ui.recipe_list

import androidx.lifecycle.ViewModel
import com.example.composereciepeeapp.network.model.repository.RecipeRepository
import javax.inject.Inject
import javax.inject.Named

class RecipeListViewModel @Inject constructor(
    private val repository: RecipeRepository,
    private @Named("auth_token") val token: String
): ViewModel()  {
}