package composables

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource
import model.Photo

@Composable
fun PhotoList(photos: List<Photo>) {
    val state = rememberLazyListState(0)

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize(), state = state)  {
            items(items = photos, itemContent = { item ->
                PhotoRow(item) {
                    println("Hello photo url ${it.url}")
                }
            })
        }

        VerticalScrollbar(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxHeight(),
            adapter = rememberScrollbarAdapter(scrollState = state)
        )
    }

}

@Composable
fun PhotoRow(photo: Photo, onClick: (photo: Photo)->Unit) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dp(8.0f))
            .clickable { onClick.invoke(photo) })
    {
        val imageSizeModifier = Modifier.height(Dp(50.0f)).width(Dp(50.0f))
        KamelImage(
            modifier = imageSizeModifier,
            onLoading = {
                Box(modifier = imageSizeModifier, contentAlignment = Alignment.Center) {}
            },
            resource = lazyPainterResource(photo.thumbnailUrl),
            contentDescription = photo.title
        )
        Column (modifier = Modifier.padding(start = Dp(16.0f))) {
            Text(photo.title)
        }
    }
}