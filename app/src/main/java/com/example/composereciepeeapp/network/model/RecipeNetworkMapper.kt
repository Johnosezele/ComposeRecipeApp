package com.example.composereciepeeapp.network.model

import com.example.composereciepeeapp.domain.model.Recipe
import com.example.composereciepeeapp.domain.model.util.EntityMapper

class RecipeNetworkMapper : EntityMapper<RecipeNetworkEntity, Recipe> {
    override fun mapFromEntity(entity: RecipeNetworkEntity): Recipe {
        TODO("Not yet implemented")
    }

    override fun mapToEntity(domainModel: Recipe): RecipeNetworkEntity {
        TODO("Not yet implemented")
    }
}