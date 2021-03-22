package com.example.composerecipeapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.composerecipeapp.presentation.ui.recipe_list.FoodCategory
import com.example.composerecipeapp.presentation.ui.recipe_list.getAllFoodCategories
import kotlinx.coroutines.launch

@Composable
fun SearchAppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    scrollPosition: Int,
    selectedCategory: FoodCategory?,
    onChangeScrollPosition: (Int) -> Unit,
    onSelectedCategoryChanged: (String) -> Unit,


    ) {
    val keyboardController = LocalFocusManager.current

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        elevation = 8.dp
    ) {

        Column {
            Row(modifier = Modifier.fillMaxWidth(.95f).padding(8.dp)) {
                TextField(
                    value = query,
                    onValueChange = { onQueryChanged(it) },
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
                            onExecuteSearch()
                            keyboardController.clearFocus()
                        }
                    )

                )
                Icon(
                    Icons.Filled.Search, contentDescription = "Search",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            val scrollState = rememberLazyListState()
            val scope = rememberCoroutineScope()

            LazyRow(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                state = scrollState
            ) {
                scope.launch { scrollState.scrollToItem(scrollPosition) }
                itemsIndexed(items = getAllFoodCategories()) { index, item ->
                    FoodCategoryChip(category = item.value,
                        isSelectedCategory = item == selectedCategory,
                        onExecuteSearch = { onExecuteSearch() },
                        onSelectedCategoryChange = {
                            onSelectedCategoryChanged(it)
                            onChangeScrollPosition(index)
                        })
                }

            }

        }
    }
}