package com.example.composerecipeapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.constraintlayout.compose.ConstraintLayout

@ExperimentalMaterialApi
@Composable
fun DecoupledSnackbarDemo(snackbarHostState: SnackbarHostState) {

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val snackbar = createRef()
        SnackbarHost(hostState = snackbarHostState, modifier = Modifier.constrainAs(snackbar) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, snackbar = {
            Snackbar(action = {
                TextButton(
                    onClick = {
                        snackbarHostState.currentSnackbarData?.dismiss()
                    }) {
                    Text(
                        text = snackbarHostState.currentSnackbarData?.actionLabel ?: "",
                        style = TextStyle(color = Color.White)
                    )
                }
            }) {
                Text(snackbarHostState.currentSnackbarData?.message ?: "")
            }
        })

    }
}


@Composable
fun SimpleSnackbarDemo(
    show: Boolean,
    onHideSnackbar: () -> Unit,
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val snackbar = createRef()
        if (show) {
            Snackbar(
                modifier = Modifier.constrainAs(snackbar) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                action = {
                    Text(
                        "Hide",
                        modifier = Modifier.clickable(onClick = onHideSnackbar),
                        style = MaterialTheme.typography.h5
                    )
                },
            ) {
                Text(text = "Hey look a snackbar")
            }
        }
    }
}







