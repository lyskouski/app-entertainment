package com.tercad.zabauka.screen.dressup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun ImageSelector(imageResources: List<DrawableResource>, default: DrawableResource) {
    var selectedImagePainter by remember { mutableStateOf(default) }
    var expanded by remember { mutableStateOf(false) }

    Box {
        Image(
            painter = painterResource(selectedImagePainter),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .clickable { expanded = true }
        )
        if (expanded) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(Color.White)
            ) {
                imageResources.forEach { imageRes ->
                    DropdownMenuItem(onClick = {
                        selectedImagePainter = imageRes
                        expanded = false
                    }) {
                        Image(
                            painter = painterResource(imageRes),
                            contentDescription = null,
                            modifier = Modifier.size(200.dp)
                        )
                    }
                }
            }
        }
    }
}