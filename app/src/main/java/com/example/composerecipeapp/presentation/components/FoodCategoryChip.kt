package com.example.composerecipeapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composerecipeapp.presentation.ui.recipe_list.FoodCategory

@Composable
fun FoodCategoryChip(category: String, onExecuteSearch: (String) -> Unit) {
    Surface(
        modifier = Modifier.padding(end = 8.dp),
        elevation = 8.dp,
        color = MaterialTheme.colors.primary,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(modifier = Modifier.clickable { onExecuteSearch.invoke(category) }) {
            Text(
                text = category,
                modifier = Modifier.padding(8.dp),
                color = Color.White,
                style = MaterialTheme.typography.body2
            )

        }
    }
}