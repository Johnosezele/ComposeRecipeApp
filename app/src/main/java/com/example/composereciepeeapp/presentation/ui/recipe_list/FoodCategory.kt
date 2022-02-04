package com.example.composereciepeeapp.presentation.ui.recipe_list

import com.example.composereciepeeapp.presentation.ui.recipe_list.FoodCategory.*

//Enum class that shows strings that we'll be searching in the searchbar
enum class FoodCategory(val value: String){
    CHICKEN("Chicken"),
    BEEF("Beef"),
    SOUP("Soup"),
    DESSERT("Dessert"),
    VEGETARIAN("Vegetarian"),
    MILK("Milk"),
    VEGAN("Vegan"),
    PIZZA("Pizza"),
    DONUT("Donut")

}

//function to lookup a specific string depending on the enum
fun getAllFoodCategories(): List<FoodCategory>{
    return listOf(CHICKEN, BEEF, SOUP, DESSERT, VEGETARIAN, MILK, VEGAN, PIZZA, DONUT)
}

//function to search a particular enum given a string. It returns a nullable food category
//which returns null incase the string matches nothing
fun getFoodCategory(value: String): FoodCategory?{

    //hashMap
    val map = FoodCategory.values().associateBy(FoodCategory::value)
    return map[value]
}