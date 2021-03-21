package com.example.composerecipeapp.di

import com.example.composerecipeapp.network.RecipeService
import com.example.composerecipeapp.network.model.RecipeDtoMapper
import com.example.composerecipeapp.repository.RecipeRepository
import com.example.composerecipeapp.repository.RecipeRepository_Impl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeMapper: RecipeDtoMapper,
    ): RecipeRepository{
        return RecipeRepository_Impl(
            recipeService = recipeService,
            mapper = recipeMapper
        )
    }
}
