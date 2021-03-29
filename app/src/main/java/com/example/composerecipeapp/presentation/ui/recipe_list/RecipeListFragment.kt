package com.example.composerecipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.composerecipeapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

// for a `var` variable also add
import androidx.compose.runtime.setValue

// or just
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composerecipeapp.presentation.components.*

import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RecipeListFragment : Fragment() {


    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                val recipes = viewModel.recipes.value
                val query = viewModel.query.value
                val selectedCategory = viewModel.selectedCategory.value
                val isLoading = viewModel.loading.value
                Column() {
                    SearchAppBar(
                        query = query,
                        onQueryChanged = viewModel::onQueryChanged,
                        onExecuteSearch = viewModel::newSearch,
                        scrollPosition = viewModel.categoryScrollPosition,
                        selectedCategory = selectedCategory,
                        onChangeScrollPosition = viewModel::onChangeCategoryScrollPosition,
                        onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged
                    )



                    Box {

                        if (isLoading) {
                            LoadingRecipeListShimmer(imageHeight = 250.dp)
                        } else {
                            LazyColumn() {
                                itemsIndexed(items = recipes) { index, recipe ->
                                    RecipeCard(recipe = recipe, onClick = {
                                        findNavController().navigate(R.id.viewRecipe)
                                    })
                                }
                            }
                        }


                        CircularIndeterminateProgressBar(isDisplayed = isLoading)
                    }

                }


            }
        }
    }
}