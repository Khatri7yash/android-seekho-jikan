package com.skhojkn.seekhojikan.presentation.common.textui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.skhojkn.seekhojikan.presentation.theme.subTitlePrimary

@Composable
fun SubtitlePrimary(text: String, color: Color = MaterialTheme.colorScheme.onPrimary) {
    Text(
        text = text,
        style = MaterialTheme.typography.subTitlePrimary,
//        color = color,
    )
}