package com.example.composerecipeapp.di

import com.example.composerecipeapp.network.RecipeService
import com.example.composerecipeapp.network.model.RecipeDtoMapper
import com.google.gson.GsonBuilder

//@Module
//@InstallIn(ApplicationComponent::class)
//object NetworkModule{
//    @Singleton
//    @Provides
//    fun provideRecipeMapper(): RecipeDtoMapper {
//        return RecipeDtoMapper()
//    }
//
//    @Singleton
//    @Provides
//    fun provideRecipeService(): RecipeService {
//        return Retrofit.Builder()
//            .baseUrl("https://food2fork.ca/api/recipe/")
//            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
//            .build()
//            .create(RecipeService::class.java)
//    }
//
//    @Singleton
//    @Provides
//    @Named("auth_token")
//    fun provideAuthToken():String{
//        return "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
//    }
//
//}