package com.example.composereciepeeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.composereciepeeapp.R
import com.example.composereciepeeapp.presentation.ui.Components.FoodCategoryChip
import com.example.composereciepeeapp.presentation.ui.Components.RecipeCard
import com.example.composereciepeeapp.util.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment: Fragment() {

    val viewModel: RecipeListViewModel by viewModels()


    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {

                //observing your results
                val recipes = viewModel.recipes.value

                //get user text input from viewModel
                val query = viewModel.query.value

                val keyboardController = LocalSoftwareKeyboardController.current

                Column {

                    //to clear onScreen keyboard
                    val focusManager = LocalFocusManager.current

                    //Building the custom toolbar
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = Color.White,
                        elevation = 8.dp,
                    ){
                        Column {

                            Row(modifier = Modifier.fillMaxWidth()) {

                                //To Capture user inputs
                                TextField(
                                    modifier = Modifier
                                        .fillMaxWidth(0.9f)
                                        .padding(8.dp),

                                    value = query, //-> this is the default value gotten from the viewModel,
                                    //  this is stored in the viewModel so that data will not be lost
                                    //  due to device configuration changes.
                                    onValueChange = {
                                        //New value entered by the user gotten from viewModel
                                        //due to configuration changes.
                                            newValue -> viewModel.onQueryChanged(newValue)
                                    },

                                    label = {
                                        Text(text = "Search")
                                    },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Text,
                                        imeAction = ImeAction.Search
                                    ),
                                    leadingIcon = {
                                        Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                                    },
                                    keyboardActions = KeyboardActions(
                                        onSearch = {
                                            ImeAction.Search
                                            viewModel.newSearch(query)
                                            keyboardController?.hide()
                                        }
                                    ),
                                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                                    colors = TextFieldDefaults.textFieldColors(
                                        backgroundColor = MaterialTheme.colors.surface)


                                )
                            }

                            //Horizontal Scrollable Row
                            Row(modifier = Modifier
                                .horizontalScroll(rememberScrollState())
                                .fillMaxWidth()) {
                                for (category in getAllFoodCategories()){
                                    FoodCategoryChip(
                                        category = category.value,
                                        onExecuteSearch = {
                                            viewModel.onQueryChanged(it)
                                            viewModel.newSearch(it)
                                        }
                                    )
                                }
                            }
                        }


                        }

                    //RecyclerView implementation in Jetpack compose
                    LazyColumn{
                        itemsIndexed(
                            items = recipes
                        ){index, recipe ->
                            RecipeCard(recipe = recipe, onClick = {})
                        }
                    }
                    }

            }
        }
    }
}

//private operator fun ImeAction.not(): Boolean {
//    TODO("Not yet implemented")
//}
