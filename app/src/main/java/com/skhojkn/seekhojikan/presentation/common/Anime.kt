package com.skhojkn.seekhojikan.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.skhojkn.seekhojikan.R
import com.skhojkn.seekhojikan.domain.model.DataItem
import com.skhojkn.seekhojikan.presentation.theme.PurpleGrey40
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin


@Composable
fun Anime(
    items: List<DataItem?>?,
    onclick: (DataItem) -> Unit
) {
    Column(
        modifier = Modifier
    ) {
        DisplayAnime(
            items,
            onclick
        )
    }

}

@Composable
fun DisplayAnime(
    items: List<DataItem?>?,
    onclick: (DataItem) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(horizontal = 5.dp)
    ) {
        items?.let {
            items(it.size) { index ->
                it[index]?.let { mItem ->
                    AnimeCard(
                        modifier = Modifier
                            .height(250.dp)
                            .testTag(mItem.malId.toString()),
                        item = mItem,
                        onclick = onclick
                    )
                }
            }
        }
    }
}

@Composable
fun AnimeCard(
    modifier: Modifier = Modifier,
    item: DataItem,
    onclick: (DataItem) -> Unit
) {
    Box(
        modifier = modifier
            .padding(6.dp)
//            .aspectRatio(0.77f)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onclick(item) }
    ) {

        GlideImage(
            imageModel = { item.images?.jpg?.imageUrl },
            modifier = Modifier.fillMaxSize(),
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            ),
            requestOptions = {
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.profile_img)
            },
            component = rememberImageComponent {
                +ShimmerPlugin(
                    shimmer = Shimmer.Flash(
                        baseColor = MaterialTheme.colorScheme.onSecondary,
                        highlightColor = MaterialTheme.colorScheme.surface
                    )
                )
            },
            loading = {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(30.dp).align(Alignment.Center)
                    )
                }
            },
            failure = {
                Box(
                    modifier = Modifier.fillMaxSize().background(PurpleGrey40),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.titleEnglish ?: "Unknown",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.9f)),
                        startY = 300f
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(10.dp)
        ) {
            Text(
                text = item.titleEnglish ?: item.title ?: "No Title",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "${item.episodes ?: "?"} Eps",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.8f)
                )
                Text(
                    text = "•",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.8f)
                )
                Text(
                    text = "★ ${item.score ?: "N/A"}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Yellow
                )
            }
        }
    }
}