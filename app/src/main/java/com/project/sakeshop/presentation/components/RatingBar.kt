package com.project.sakeshop.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun RatingBar(
    rating: Double,
    modifier: Modifier = Modifier,
    stars: Int = 5,
    starsColor: Color = Color(0xFFFFC107)
) {
    Row(modifier = modifier) {
        for (i in 1..stars) {
            val icon = when {
                i <= floor(rating) -> Icons.Filled.Star
                i <= ceil(rating) && rating % 1 != 0.0 -> Icons.AutoMirrored.Filled.StarHalf
                else -> Icons.Filled.StarOutline
            }

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}