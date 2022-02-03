package com.example.composereciepeeapp.presentation.ui.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composereciepeeapp.domain.model.Recipe
import com.example.composereciepeeapp.network.model.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val repository: RecipeRepository,
    private @Named("auth_token") val token: String
): ViewModel()  {

    //observing state changes
    val recipes: MutableState<List<Recipe>> = mutableStateOf(listOf())

    //persist search input which may be lost due to configuration changes such as screen rotation
    val query = mutableStateOf("Chicken")

    init {
        newSearch("Chicken")
    }

    //asynchronous loading from API
    fun newSearch(query: String){
        viewModelScope.launch {
            val result = repository.search(
                token = token,
                page = 1,
                query = query,
            )
            recipes.value = result
        }
    }

    //function that will enable user edit the default searched text
    fun onQueryChanged(query: String){
        this.query.value = query
    }
}