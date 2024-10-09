package com.r4z0r.focamah.componentes

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.r4z0r.focamah.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Scaffold(
    modifier: Modifier = Modifier,
    context: Context? = null
) {
    var counterJob: Job? = null;
    val defaultRoundTime = 1500 //1500
    val defaultRestTime = 300 //300

    var isCounting: Boolean by remember { mutableStateOf(false) }
    var clickable: Boolean by remember { mutableStateOf(true) }
    var remainingTime: Int by remember { mutableIntStateOf(0) }
    var round by remember { mutableIntStateOf(0) }

    val coroutineContext = Dispatchers.Main

    fun pauseCounter() {
        counterJob?.cancel()
        counterJob = null
        isCounting = false
    }

    val counterScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    fun playAudio(audioResId: Int) {
        val mediaPlayer = MediaPlayer.create(context, audioResId)
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener { it.release() }
    }

    fun resetCounter() {
        if (clickable) {
            pauseCounter()
            round = 0
            remainingTime = 0
            clickable = true
        }
    }

    suspend fun handleRoundCompletion() {
        round++

        if (round > 8) {
            playAudio(R.raw.acabou_acabou_acabou_galvao_bueno)
            isCounting = false
            round = 0
            counterJob?.cancelAndJoin()
            return
        }

        clickable = false

        if (round % 2 == 0) {
            playAudio(R.raw.agora_vou_descansar)
            remainingTime = if (round != 2) defaultRestTime else defaultRestTime * 3
        } else {
            playAudio(R.raw.vai_comecar_a_p0taria)
            remainingTime = defaultRoundTime
        }
    }

    fun startCounter() {
        if (counterJob == null || counterJob?.isCompleted == true) {
            counterJob = counterScope.launch {
                clickable = false
                isCounting = true
                while (isActive) {
                    if (remainingTime > 0) {
                        remainingTime -= 1
                        println("Counter: $remainingTime")
                        delay(1000L)
                    } else {
                        handleRoundCompletion()
                    }
                    clickable = true
                }
            }
        }
    }

    fun resumeCounter() {
        if (!isCounting) {
            startCounter()
        }
    }

    androidx.compose.material3.Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingButtons(
                isRunning = isCounting,
                onReset = { resetCounter() },
                onPause = { pauseCounter() },
                onResume = { resumeCounter() },
                round = round,
                remainingTime = remainingTime,
                isClickable = clickable,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        },
        topBar = {
            TopAppBar(title = {
                Text(text = "Foca! Mah!")
            }, colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer))
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Contador(remainingTime)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Ciclo: ",
                    style = MaterialTheme.typography.titleLarge.plus(
                        TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp
                        )
                    ),
                )
                Text(
                    "$round de 8",
                    style = MaterialTheme.typography.titleLarge.plus(TextStyle(fontSize = 32.sp)),
                )
            }
            AnimatedVisibility(
                visible = round in 1..7,
                enter = slideInVertically(initialOffsetY = { it * 2 }),
                exit = slideOutVertically(targetOffsetY = { it * 2 }),
            ) {
                CartaoStatus(isResting = round % 2 == 0)
            }
        }
    }
}

@Preview(name = "Scaffold")
@Composable
private fun PreviewScaffold() {
    Scaffold()
}