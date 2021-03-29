package com.example.composerecipeapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout

@Composable
fun CircularIndeterminateProgressBar(isDisplayed: Boolean) {

    if (isDisplayed) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (progressBar) = createRefs()
            val topBias = createGuidelineFromTop(0.3f)
            CircularProgressIndicator(
                modifier = Modifier.constrainAs(progressBar) {
                    top.linkTo(topBias)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                color = MaterialTheme.colors.primary
            )
        }

    }
}