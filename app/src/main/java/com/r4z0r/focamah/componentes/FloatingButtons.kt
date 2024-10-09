package com.r4z0r.focamah.componentes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.r4z0r.focamah.ui.theme.errorContainerDarkMediumContrast

@Composable
fun FloatingButtons(
    isRunning: Boolean,
    onReset: () -> Unit,
    onPause: () -> Unit,
    onResume: () -> Unit,
    round: Int,
    remainingTime: Int,
    isClickable: Boolean,
    modifier: Modifier
) {
    Row(modifier = modifier) {
        FloatingActionButton(
            modifier = Modifier.size(72.dp),
            onClick = {
                if (isClickable) {
                    if (isRunning) {
                        onPause()
                    } else {
                        onResume()
                    }
                }
            }// Distribute space evenly
        ) {
            PlayPauseIcon(isRunning)
        }
        AnimatedVisibility(
            visible = round > 0,
            enter = slideInVertically(initialOffsetY = { it * 2 }),
            exit = slideOutVertically(targetOffsetY = { it * 2 }),
        ) {
            FloatingActionButton(
                onClick = { if (isClickable) onReset() },
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(72.dp),
                containerColor = errorContainerDarkMediumContrast
            ) {
                Icon(imageVector = Icons.Default.Restore, contentDescription = "Reset")
            }
        }
    }
}

@Composable
private fun PlayPauseIcon(isRunning: Boolean) {
    Crossfade(targetState = isRunning, label = "") { isRunning ->
        if (isRunning) {
            Icon(imageVector = Icons.Default.Pause, contentDescription = "Pause")
        } else {
            Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Play")
        }
    }
}

@Preview(name = "FloatingButtons")
@Composable
private fun PreviewFloatingButtons() {
    FloatingButtons(
        isRunning = true,
        onReset = {},
        onPause = {},
        onResume = {},
        round = 1,
        remainingTime = 10,
        isClickable = true,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}