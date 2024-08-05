package com.tercad.zabauka.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tercad.zabauka.screen.dressup.ImageSelector
import org.jetbrains.compose.resources.stringResource

import zabauka.composeapp.generated.resources.Res
import zabauka.composeapp.generated.resources.appDressUp
import zabauka.composeapp.generated.resources.btnBack
import zabauka.composeapp.generated.resources.compose_multiplatform
import zabauka.composeapp.generated.resources.compose_multiplatform1
import zabauka.composeapp.generated.resources.compose_multiplatform2

class DressUpScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val scrollState = rememberScrollState()

        val imageResources = listOf(
            Res.drawable.compose_multiplatform,
            Res.drawable.compose_multiplatform1,
            Res.drawable.compose_multiplatform2
        )
        val default = Res.drawable.compose_multiplatform

        Scaffold(topBar = {
            TopAppBar(title = {
                Text(stringResource(Res.string.appDressUp))
            },
                navigationIcon = {
                    IconButton({ navigator.pop() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.btnBack)
                        )
                    }
                })
        }, content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                color = MaterialTheme.colors.primary
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    ImageSelector(imageResources, default)
                    ImageSelector(imageResources, default)
                    ImageSelector(imageResources, default)
                }
            }
        })
    }
}