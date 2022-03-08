// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import composables.PhotoList
import io.kamel.core.config.KamelConfig
import io.kamel.core.config.takeFrom
import io.kamel.image.config.Default
import io.kamel.image.config.resourcesFetcher
import model.Photo
import services.PhotosApi

@Composable
fun App() {
    var photos by remember { mutableStateOf(listOf<Photo>()) }
    var loading by remember { mutableStateOf(false) }

    LaunchedEffect(loading) {
        if(loading) {
            photos = PhotosApi.getPhotos()
            loading = false
        }
    }

    MaterialTheme {
        Scaffold (
            topBar = {
                TopAppBar (
                    title = { Text("Photos Demo App") },
                    actions = {
                        IconButton(onClick = { loading = true }) {
                            Icon(Icons.Filled.Refresh, contentDescription = "Load photos")
                        }
                    }
                )
            }
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                if (loading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else {
                    PhotoList(photos)
                }
            }
        }
    }
}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
