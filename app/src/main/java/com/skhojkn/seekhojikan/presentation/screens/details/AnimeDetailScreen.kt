package com.skhojkn.seekhojikan.presentation.screens.details

import androidx.annotation.OptIn
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.skhojkn.seekhojikan.domain.model.AnimeDetails
import com.skhojkn.seekhojikan.domain.usecase.network.Result
import com.skhojkn.seekhojikan.presentation.common.BaseColumn
import com.skhojkn.seekhojikan.presentation.common.BaseScreen
import com.skhojkn.seekhojikan.presentation.common.textui.ExpandableText
import com.skhojkn.seekhojikan.presentation.common.textui.SubtitlePrimary
import com.skhojkn.seekhojikan.presentation.common.textui.SubtitleSecondary
import com.skhojkn.seekhojikan.presentation.navigation.Screen
import com.skhojkn.seekhojikan.presentation.theme.pinkColor
import com.skhojkn.seekhojikan.presentation.theme.starColor
import com.skhojkn.seekhojikan.presentation.utils.annotation.ThemePreview
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import androidx.core.net.toUri
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.skhojkn.seekhojikan.domain.model.Data
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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
    val context = LocalContext.current
    val density = LocalDensity.current
    var bHeightPx by remember { mutableStateOf(0f) }
    var posterWidth by remember { mutableStateOf(0f) }
    var hasTrailer by remember { mutableStateOf(false) }
    val title by remember { mutableStateOf("Anime Details") }

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            repeatMode = Player.REPEAT_MODE_ONE
            playWhenReady = true
        }
    }

//    DisposableEffect(animeDetailsState) {
//        if (animeDetailsState is Result.Success) {
//            val videoId = extractYoutubeId(animeDetailsState.data.data?.trailer?.embedUrl)
//            val videoUrl =
//                animeDetailsState.data.data?.trailer?.embedUrl
////            videoUrl?.let {
////                hasTrailer = true
////                val mediaItem = MediaItem.fromUri(videoUrl.toUri())
////                exoPlayer.setMediaItem(mediaItem)
////                exoPlayer.prepare()
////            }
//            videoId?.let {
//                hasTrailer = true
//            }
//        }
//        onDispose { exoPlayer.release() }
//    }



    BaseScreen(
        title = title,
        navigation = navigation
    ) {
        BaseColumn(state = animeDetailsState) {
            if (animeDetailsState is Result.Success) {
                val details = animeDetailsState.data.data
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

                        PosterView(
                            details,
                            hasTrailer,
                            exoPlayer,
                            onHeightMeasured = { measuredHeight ->
                                bHeightPx = measuredHeight
                            })

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

                                    SubtitlePrimary(text = "Episodes")
                                    SubtitleSecondary(
                                        text = "${details?.episodes ?: "-"}"
                                    )


                                    Spacer(Modifier.height(5.dp))

                                    SubtitlePrimary(text = "Favorites")
                                    Text(
                                        text = "♥ ${details?.favorites ?: "N/A"}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = pinkColor
                                    )

                                }

                                Column(
                                    Modifier.weight(1f),
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    SubtitlePrimary(text = "Duration")
                                    SubtitleSecondary(text = details?.duration ?: "-")

                                    Spacer(Modifier.height(5.dp))

                                    SubtitlePrimary(text = "Rating")
                                    Text(
                                        text = "★ ${details?.score ?: "N/A"}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = starColor
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
                        ExpandableText(
                            Modifier,
                            text = details?.synopsis ?: "Unknown",
                            textModifier = Modifier
                        )
                    }

                }
            }
        }
    }
}

@OptIn(UnstableApi::class)
@Composable
fun PosterView(
    details: Data?, hasTrailer: Boolean, exoPlayer: ExoPlayer,
    onHeightMeasured: (Float) -> Unit
) {

    val scope = rememberCoroutineScope()
    var videoId by remember { mutableStateOf(extractYoutubeId(details?.trailer?.embedUrl))}

    val modifier = Modifier
        .onGloballyPositioned { coords ->
            onHeightMeasured(coords.size.height.toFloat())
        }

    if (videoId != null) {
        AndroidView(
            factory = { ctx ->
                YouTubePlayerView(ctx).apply {
                    addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.mute()
                            videoId?.let {
                                youTubePlayer.loadVideo(it, 0f)
                            }
                        }

                        override fun onError(
                            youTubePlayer: YouTubePlayer,
                            error: PlayerConstants.PlayerError
                        ) {
                            super.onError(youTubePlayer, error)
                            scope.launch {
                                delay(1000L)
                                videoId = null
                            }
                        }
                    })
                }
            },
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(3f / 2f)
                .graphicsLayer {
                renderEffect = BlurEffect(5f, 5f) // Keeps your blur effect
                alpha = 0.9f
            }
        )
//        AndroidView(
//            factory = { ctx ->
//                PlayerView(ctx).apply {
//                    player = exoPlayer
//                    useController = false // Hide play/pause buttons
//                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
//                }
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .aspectRatio(3f / 2f)
//                .graphicsLayer {
//                    alpha = 0.9f
//                    renderEffect = BlurEffect(5f, 5f) // Keeps your blur effect
//                }
//                .onGloballyPositioned { coords ->
//                    onHeightMeasured(coords.boundsInParent().height)
//                }
//        )
    } else {
        GlideImage(
            modifier = modifier
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
                }/*
                .onGloballyPositioned { coords ->
                    onHeightMeasured(coords.boundsInParent().height)
                }*/,
            imageModel = { details?.images?.jpg?.imageUrl },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop
            )
        )
    }
}


fun extractYoutubeId(url: String?): String? {
    return url?.substringAfter("embed/")?.substringBefore("?")
}


@ThemePreview
@Composable
private fun PreviewDetailsScreen() {
//    AnimeDetails(
//        Result.Success()
//    )
}