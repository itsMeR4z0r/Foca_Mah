package com.r4z0r.focamah.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val contadorTextStyle =
    TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 72.sp,
        letterSpacing = 0.15.sp
    )

val timeUnitColumnModifier = Modifier.background(
    Color.DarkGray,
    RoundedCornerShape(25)
)

fun converterTempo(tempo: Int): Pair<String, String> {
    val minutos = tempo / 60
    val segundos = tempo % 60

    val minutosStr = if (minutos < 10) "0$minutos" else minutos.toString()
    val segundosStr = if (segundos < 10) "0$segundos" else segundos.toString()

    return Pair(minutosStr, segundosStr)
}

@Composable
fun Contador(tempo: Int) {

    val tempoString by rememberUpdatedState(newValue = converterTempo(tempo))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
    ) {
        TimeUnitDisplay(time = tempoString.first, modifier = timeUnitColumnModifier)
        Spacer(modifier = Modifier.width(16.dp))
        Text(":", style = TextStyle(fontWeight = FontWeight.ExtraBold, fontSize = 64.sp))
        Spacer(modifier = Modifier.width(16.dp))
        TimeUnitDisplay(time = tempoString.second, modifier = timeUnitColumnModifier)
    }
}

@Composable
fun TimeUnitDisplay(time: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            time,
            modifier = Modifier.padding(16.dp),
            color = Color.White,
            style = contadorTextStyle
        )
    }
}