package com.example.composereciepeeapp.presentation.ui.Components

import android.annotation.SuppressLint
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.composereciepeeapp.presentation.ui.recipe_list.FoodCategory
import com.example.composereciepeeapp.presentation.ui.recipe_list.getAllFoodCategories
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalComposeUiApi
@Composable
fun SearchAppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    scrollPosition: Float,
    selectedCategory: FoodCategory?,
    onSelectedCategoryChanged: (String) -> Unit,
    onChangedCategoryScrollPosition: (Float) -> Unit,
){
    //to clear onScreen keyboard
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

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
                            newValue -> onQueryChanged(newValue)
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
                            onExecuteSearch()
                            keyboardController?.hide()
                        }
                    ),
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface)


                )
            }

            //setting scroll position
            val scrollState = rememberScrollState()
            val coroutineScope = rememberCoroutineScope()

            //Horizontal Scrollable Row
            Row(modifier = Modifier
                .horizontalScroll(scrollState)
                .fillMaxWidth()
                .padding(start = 8.dp, bottom = 8.dp),
            ) {
                coroutineScope.launch {
                    scrollState.scrollTo(scrollPosition.toInt())
                }
                for (category in getAllFoodCategories()){
                    FoodCategoryChip(
                        category = category.value,
                        isSelected = selectedCategory == category,
                        onSelectedCategoryChanged = {onSelectedCategoryChanged(it)
                            onChangedCategoryScrollPosition(scrollState.value.toFloat())},
                        onExecuteSearch = {
                            onExecuteSearch()
                        },
                    )
                }
            }
        }
    }
}