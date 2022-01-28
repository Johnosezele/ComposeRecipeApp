package com.example.composereciepeeapp.di

import com.example.composereciepeeapp.network.model.RecipeDtoMapper
import com.example.composereciepeeapp.network.model.RecipeService
import com.example.composereciepeeapp.network.model.repository.RecipeRepository
import com.example.composereciepeeapp.network.model.repository.RecipeRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService, recipeDtoMapper: RecipeDtoMapper
    ): RecipeRepository{
        return RecipeRepository_Impl(recipeService, recipeDtoMapper)
    }
}