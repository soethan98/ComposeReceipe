package com.example.composerecipeapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.composerecipeapp.R
import com.example.composerecipeapp.domain.model.Recipe
import com.example.composerecipeapp.util.loadPicture
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@Composable
fun RecipeCard(
    recipe: Recipe,
    onClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.padding(bottom = 6.dp, top = 6.dp).fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp
    ) {
        Column() {
            recipe.featuredImage?.let { url ->
                val image = loadPicture(url = url, defaultImage = R.drawable.empty_plate).value
                image?.let {
                    Image(
                        bitmap = it.asImageBitmap(), contentDescription = recipe.title,
                        modifier = Modifier.fillMaxWidth().height(225.dp),
                        contentScale = ContentScale.Crop
                    )
                }

            }

            recipe.title?.let { title ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(
                            start = 8.dp,
                            end = 8.dp,
                            top = 12.dp,
                            bottom = 12.dp
                        )
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.fillMaxWidth(fraction = 0.85f)
                            .wrapContentWidth(Alignment.Start)
                    )
                    val rank = recipe.rating.toString()
                    Text(
                        text = rank,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.h6
                    )
                }
            }
        }
    }
}