import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ImageSelector(imageResources: List<String>, default: String) {
    var selectedImagePainter by remember { mutableStateOf(default) }
    var expanded by remember { mutableStateOf(false) }

    Box {
        Image(
            painter = painterResource(resourcePath = selectedImagePainter),
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
                            painter = painterResource(resourcePath = imageRes),
                            contentDescription = null,
                            modifier = Modifier.size(200.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun App() {
    val imageResources = listOf(
        "drawable/compose-multiplatform.xml",
        "drawable/compose-multiplatform1.xml",
        "drawable/compose-multiplatform2.xml"
    )
    val default = "drawable/compose-multiplatform.xml"

    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painter = painterResource(resourcePath = "drawable/compose-multiplatform.xml"), contentDescription = null)
                    Text("Compose: $greeting")
                }
            }
            ImageSelector(imageResources, default)
            ImageSelector(imageResources, default)
            ImageSelector(imageResources, default)
        }
    }
}
