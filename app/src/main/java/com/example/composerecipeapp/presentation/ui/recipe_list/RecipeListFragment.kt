package com.example.composerecipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import com.example.composerecipeapp.R
import com.example.composerecipeapp.presentation.components.FoodCategoryChip
import com.example.composerecipeapp.presentation.components.RecipeCard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

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
                val keyboardController = LocalFocusManager.current
                Column() {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        elevation = 8.dp
                    ) {

                        Column() {
                            Row(modifier = Modifier.fillMaxWidth(.95f).padding(8.dp)) {
                                TextField(
                                    value = query,
                                    onValueChange = { viewModel.onQueryChanged(it) },
                                    label = {
                                        Text(text = "Search")
                                    },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Text,
                                        imeAction = ImeAction.Done,
                                    ),

                                    leadingIcon = {
                                        Icon(Icons.Filled.Search, contentDescription = "Icons")
                                    },
                                    colors = TextFieldDefaults.textFieldColors(
                                        backgroundColor = MaterialTheme.colors.surface,
                                        textColor = MaterialTheme.colors.onSurface
                                    ),
                                    keyboardActions = KeyboardActions(
                                        onDone = {
                                            viewModel.newSearch(query = query)
                                            keyboardController.clearFocus()
                                        }
                                    )

                                )
                                Icon(
                                    Icons.Filled.Search, contentDescription = "Search",
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                            }
                            LazyRow(modifier = Modifier.padding(horizontal = 8.dp)) {
                                itemsIndexed(items = getAllFoodCategories()) { index, item ->
                                    FoodCategoryChip(category = item.value, onExecuteSearch = {
                                        viewModel.onQueryChanged(it)
                                        viewModel.newSearch(it)
                                    })
                                }

                            }

                        }
                    }
                    LazyColumn() {
                        itemsIndexed(items = recipes) { index, recipe ->
                            RecipeCard(recipe = recipe, onClick = {
                                findNavController().navigate(R.id.viewRecipe)
                            })
                        }
                    }
                }


            }
        }
    }
}