package com.example.composereciepeeapp.presentation.ui.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composereciepeeapp.domain.model.Recipe
import com.example.composereciepeeapp.network.model.repository.RecipeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class RecipeListViewModel @Inject constructor(
    private val repository: RecipeRepository,
    private @Named("auth_token") val token: String
): ViewModel()  {

    //observing state changes
    val recipes: MutableState<List<Recipe>> = mutableStateOf(listOf())

    init {
        viewModelScope.launch {
            val result = repository.search(
                token = token,
                page = 1,
                query = "chicken",
            )
            recipes.value = result
        }
    }

}