package com.skhojkn.seekhojikan.presentation.screens.details

import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skhojkn.seekhojikan.domain.model.AnimeDetails
import com.skhojkn.seekhojikan.domain.usecase.network.Result
import com.skhojkn.seekhojikan.presentation.common.BaseColumn
import com.skhojkn.seekhojikan.presentation.common.BaseScreen
import com.skhojkn.seekhojikan.presentation.common.textui.ExpandableText
import com.skhojkn.seekhojikan.presentation.common.textui.SubtitlePrimary
import com.skhojkn.seekhojikan.presentation.common.textui.SubtitleSecondary
import com.skhojkn.seekhojikan.presentation.navigation.Screen
import com.skhojkn.seekhojikan.presentation.utils.annotation.ThemePreview
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin


@Composable
fun AnimeDetailsScreen(animeID: Int, navigation: (Screen?, Array<out Any>?) -> Unit) {

    val viewModel = hiltViewModel<AnimeDetailViewModel>()
    val animeDetailsState by viewModel.animeDetail.collectAsState()

    LaunchedEffect(animeID) {
        viewModel.fetchAnimeDetails(animeID)
    }

    AnimeDetails(
        animeDetailsState = animeDetailsState,
        navigation = navigation
    )
}


@Composable
private fun AnimeDetails(
    animeDetailsState: Result<AnimeDetails>,
    navigation: (Screen?, Array<out Any>?) -> Unit = { nav, arr ->
    }
) {


    val density = LocalDensity.current
    var bHeightPx by remember { mutableStateOf(0f) }
    var posterWidth by remember { mutableStateOf(0f) }
    val title by remember { mutableStateOf("Anime Details") }
    BaseScreen(
        title = title,
        navigation = navigation
    ) {
        BaseColumn(state = animeDetailsState) {
            if (animeDetailsState is Result.Success) {
                val details = animeDetailsState.data?.data
                Column(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                ) {

                    Box(
                        Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                    ) {
                        GlideImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(3f / 2f)
                                .graphicsLayer {
                                    alpha = 0.9f
                                    scaleX = 1f
                                    scaleY = 1f
                                    translationX = 0f
                                    translationY = 0f
                                    shadowElevation = 10f
                                    ambientShadowColor = Color.Black
                                    spotShadowColor = Color.Black
                                    renderEffect = BlurEffect(5f, 5f)
                                }
                                .onGloballyPositioned { coords ->
                                    bHeightPx = coords.boundsInParent().height
                                },
                            imageModel = { details?.images?.jpg?.imageUrl },
                            imageOptions = ImageOptions(
                                contentScale = ContentScale.Crop
                            )
                        )
                        val bgHeight = with(density) { bHeightPx.toDp() }
                        Row(
                            Modifier
                                .offset { IntOffset(0, 0) }
                                .padding(start = 10.dp, top = max(0.dp, bgHeight - 50.dp))) {
                            GlideImage(
                                modifier = Modifier
                                    .size(135.dp, 180.dp) // Poster size (width x height)
                                    .clip(RoundedCornerShape(10.dp))
                                    .border(
                                        1.dp, Color.White, RoundedCornerShape(10.dp)
                                    )
                                    .onGloballyPositioned({ cord ->
                                        posterWidth = cord.size.width.toFloat()
                                    }),
                                imageModel = { details?.images?.jpg?.imageUrl },
                                imageOptions = ImageOptions(
                                    contentScale = ContentScale.Crop,
                                ),
                                component = rememberImageComponent {
                                    +ShimmerPlugin(
                                        shimmer = Shimmer.Flash(
                                            baseColor = MaterialTheme.colorScheme.primary,
                                            highlightColor = MaterialTheme.colorScheme.onSecondary
                                        )
                                    )
                                },
                            )
                        }
                        val posterWidthDp = with(density) { posterWidth.toDp() }
                        val contentHeight = with(density) { (bHeightPx).toDp() }
                        Column(
                            Modifier
                                .offset {
                                    IntOffset(
                                        x = 0,
                                        y = 0
                                    )
                                }
                                .fillMaxWidth()
                                .padding(
                                    start = posterWidthDp + 20.dp,
                                    top = contentHeight + 5.dp
                                )) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .basicMarquee(
                                        iterations = Int.MAX_VALUE,
                                        animationMode = MarqueeAnimationMode.Immediately,
                                        velocity = 50.dp
                                    ),
                                text = details?.titleEnglish ?: "Unknown",
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1
                            )

                            Spacer(modifier = Modifier)

                            Row {
                                Column(
                                    Modifier.weight(1f),
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    SubtitlePrimary(
                                        text = "${details?.episodes ?: "?"} Eps",
//                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.White.copy(alpha = 0.8f))


                                    Spacer(Modifier.height(5.dp))

                                    SubtitlePrimary(text = "year")
                                    SubtitleSecondary(text = details?.year.toString())

                                }

                                Column(
                                    Modifier.weight(1f),
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    SubtitlePrimary(text = "duration")
                                    SubtitleSecondary(text = details?.duration ?: "Unknown")

                                    Spacer(Modifier.height(5.dp))

                                    SubtitlePrimary(text = "Rating")
                                    Text(
                                        text = "★ ${details?.score ?: "N/A"}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.Yellow
                                    )

                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(15.dp))

                    Column(
                        Modifier
                            .padding(start = 10.dp, end = 10.dp)
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "Description:",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(Modifier.height(10.dp))
                        ExpandableText(Modifier, text = details?.synopsis ?: "Unknown", textModifier = Modifier)
                    }

                }
            }
        }
    }
}


@ThemePreview
@Composable
private fun PreviewDetailsScreen() {
//    AnimeDetails(
//        Result.Success()
//    )
}