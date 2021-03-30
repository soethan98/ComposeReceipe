package com.example.composerecipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.composerecipeapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import androidx.compose.runtime.*

// for a `var` variable also add
import androidx.compose.runtime.setValue

// or just
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.composerecipeapp.presentation.BaseApplication
import com.example.composerecipeapp.presentation.components.*
import com.example.composerecipeapp.presentation.components.util.SnackBarController
import com.example.composerecipeapp.presentation.theme.AppTheme
import kotlinx.coroutines.coroutineScope

import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()
    private val snackBarController = SnackBarController(lifecycleScope)

    @ExperimentalMaterialApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                val application = activity?.application as BaseApplication

                AppTheme(darkTheme = application.isDark.value) {
                    val recipes = viewModel.recipes.value
                    val query = viewModel.query.value
                    val selectedCategory = viewModel.selectedCategory.value
                    val isLoading = viewModel.loading.value
                    val page = viewModel.page.value
                    val scaffoldState = rememberScaffoldState()

                    Scaffold(topBar = {
                        SearchAppBar(
                            query = query,
                            onQueryChanged = viewModel::onQueryChanged,
                            onExecuteSearch = {
                                if (viewModel.selectedCategory.value?.value == "Milk") {
                                    snackBarController.getScope().launch {
                                        snackBarController.showSnackBar(
                                            scaffoldState = scaffoldState,
                                            message = "Invalid category: MILK",
                                            actionLabel = "Hide"
                                        )
                                    }
                                } else {
                                    viewModel.onTriggerEvent(RecipeListEvent.NewSearchEvent)
                                }
                            },
                            scrollPosition = viewModel.categoryScrollPosition,
                            selectedCategory = selectedCategory,
                            onChangeScrollPosition = viewModel::onChangeCategoryScrollPosition,
                            onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                            onToggleTheme = { application.toggleLightTheme() }

                        )
                    }, scaffoldState = scaffoldState,
                        snackbarHost = {
                            scaffoldState.snackbarHostState
                        }) {
                        Box(modifier = Modifier.background(MaterialTheme.colors.background)) {
                            if (isLoading && recipes.isEmpty()) {
                                LoadingRecipeListShimmer(imageHeight = 250.dp)
                            } else {
                                LazyColumn() {
                                    itemsIndexed(items = recipes) { index, recipe ->
                                        viewModel.onChangeRecipeScrollPosition(index)
                                        if ((index + 1) >= (page * PAGE_SIZE) && !isLoading) {
                                            viewModel.onTriggerEvent(RecipeListEvent.NextPageEvent)
                                        }
                                        RecipeCard(recipe = recipe, onClick = {
                                            findNavController().navigate(R.id.viewRecipe)
                                        })
                                    }
                                }
                            }
                            CircularIndeterminateProgressBar(isDisplayed = isLoading)

                            DefaultSnackbar(
                                snackbarHostState = scaffoldState.snackbarHostState,
                                modifier = Modifier.align(Alignment.BottomCenter),
                                onDismiss = {
                                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                                })
                        }

                    }
                }
            }
        }
    }
}