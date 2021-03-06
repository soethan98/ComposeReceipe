package com.example.composerecipeapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ScrollView
import android.widget.Space
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composerecipeapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

    }
}

@Composable
fun Meal() {
    Column(modifier = Modifier.fillMaxSize().background(color = Color(0xFFF2F2F2))) {
        Image(
            painter = painterResource(id = R.drawable.happy_meal_small),
            contentDescription = "Image", modifier = Modifier.height(300.dp),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Happy Meal", style = TextStyle(
                        fontSize = 26.sp
                    )
                )
                Text(
                    text = "$5.99",
                    style = TextStyle(
                        color = Color(0xFF85bb65),
                        fontSize = 17.sp
                    ),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

            }
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(
                text = "800 calories",
                style = TextStyle(
                    fontSize = 17.sp
                )
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))

//            Button(
//                onClick = { },
//                modifier = Modifier.align(Alignment.CenterHorizontally)
//            ) {
//                Text(text = "ORDER NOW")
//            }


        }
    }
}


