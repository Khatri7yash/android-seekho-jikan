package com.skhojkn.seekhojikan.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
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

    GlideImage(
        imageModel = { item.images?.jpg?.imageUrl },
        modifier = modifier
            .padding(6.dp)
            .clickable { onclick(item) }
            .clip(RoundedCornerShape(10.dp)),
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
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        },
        failure = {
            Text(modifier = modifier
                .background(PurpleGrey40)
                .clickable { onclick(item) }
                .clip(RoundedCornerShape(10.dp)),
                text = item.titleEnglish.toString(),
                textAlign = TextAlign.Center)
        }
    )
}