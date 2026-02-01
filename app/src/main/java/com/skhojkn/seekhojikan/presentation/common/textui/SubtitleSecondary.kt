package com.skhojkn.seekhojikan.presentation.common.textui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.skhojkn.seekhojikan.presentation.theme.subTitleSecondary

@Composable
fun SubtitleSecondary(text:String) {
    Text(
        text = text,
        style = MaterialTheme.typography.subTitleSecondary,
        color = MaterialTheme.colorScheme.onSecondary,
    )
}