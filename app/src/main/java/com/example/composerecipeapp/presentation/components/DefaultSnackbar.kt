package com.example.composerecipeapp.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@ExperimentalMaterialApi
@Composable
fun DefaultSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {

    SnackbarHost(hostState = snackbarHostState,
        modifier = modifier,
        snackbar = { data ->
            Snackbar(modifier = Modifier.padding(16.dp),
                action = {
                    TextButton(onClick = onDismiss) {
                        Text(
                            text = data.actionLabel ?: "Hide",
                            style = MaterialTheme.typography.body2,
                            color = Color.White
                        )
                    }
                }) {
                Text(
                    text = data?.message, color = Color.White,
                    style = MaterialTheme.typography.body2
                )

            }
        })
}