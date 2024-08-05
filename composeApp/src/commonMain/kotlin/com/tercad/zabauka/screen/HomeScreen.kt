package com.tercad.zabauka.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import zabauka.composeapp.generated.resources.Res
import zabauka.composeapp.generated.resources.compose_multiplatform
import zabauka.composeapp.generated.resources.compose_multiplatform1
import zabauka.composeapp.generated.resources.compose_multiplatform2

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val items = listOf(
            Pair(Res.drawable.compose_multiplatform, "Dress Up"),
            Pair(Res.drawable.compose_multiplatform1, "Puzzles"),
            Pair(Res.drawable.compose_multiplatform2, "Tracing Letters"),
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(200.dp),
            contentPadding = PaddingValues(10.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) {
            items(items) { item ->
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .padding(8.dp)
                        .border(2.dp, Color.Blue, RoundedCornerShape(6.dp))
                        .clickable {
                            navigator.push(DressUpScreen())
                        }
                ) {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(item.first),
                            contentDescription = null,
                            modifier = Modifier
                                .size(150.dp)
                        )
                        Text(text = item.second)
                    }
                }
            }
        }
    }
}