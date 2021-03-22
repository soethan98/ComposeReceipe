package com.example.composerecipeapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composerecipeapp.presentation.ui.recipe_list.FoodCategory

@Composable
fun FoodCategoryChip(
    category: String, isSelectedCategory: Boolean, onExecuteSearch: () -> Unit,
    onSelectedCategoryChange: (String) -> Unit
) {
    Surface(
        modifier = Modifier.padding(end = 8.dp),
        elevation = 8.dp,
        color = if (isSelectedCategory) Color.LightGray else MaterialTheme.colors.primary,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.toggleable(value = isSelectedCategory,
                onValueChange = {
                    onSelectedCategoryChange(category)
                    onExecuteSearch()
                })
        ) {
            Text(
                text = category,
                modifier = Modifier.padding(8.dp),
                color = Color.White,
                style = MaterialTheme.typography.body2
            )

        }
    }
}