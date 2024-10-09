package com.r4z0r.focamah.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.r4z0r.focamah.ui.theme.inversePrimaryLightMediumContrast
import com.r4z0r.focamah.ui.theme.tertiaryContainerLight

val workPhrases = arrayOf(
    "Vamos focar agora, daqui a pouco tem o descanso! 🦁🦅",
    "Concentração total! O descanso está logo ali! 🧘‍♂️",
    "Foquemos agora, memes depois! 💪",
    "Hora de ser produtivo! O sofá te espera! 🛋️",
    "Trabalhe como um leão, descanse como um gatinho! 🐱",
    "Vamos focar, a procrastinação fica pra depois! 🚀",
    "Atenção máxima agora, cochilo mais tarde! 😴",
    "Trabalho agora, TikTok depois! 🎵",
    "Modo produtividade: ativado! O descanso está próximo! ⚡",
    "Foco total, descanso surreal! ✨",
    "Vamos lá, guerreiro! A folga tá chegando! 🛡️",
    "Trabalho primeiro, memes depois! 😂",
    "Foquemos! O descanso vem rapidinho! ⏳",
    "Concentração agora, memes depois! 📈",
    "Trabalhe duro, descanse mole! 😎",
    "Foquemos, guerreiros! A folga vem aí! 🏹",
    "Hora de brilhar! Descanso logo ali! 🌟",
    "Produtividade máxima! Relaxamento em breve! 📚",
    "Vamos focar, depois descansamos como reis! 👑",
    "Concentração total! Netflix depois! 🍿",
    "Trabalho agora, redes sociais depois! 📱",
    "Hora de focar! O descanso vem logo! 🧘‍♀️",
    "Concentração máxima! Soneca depois! 💤",
    "Produtividade alta agora, descanso merecido depois! 🌈",
    "Vamos focar! A preguiça fica pra depois! 🦥",
    "Foco total! Memes em breve! 😂",
    "Trabalhe agora, game depois! 🎮",
    "Hora de ser produtivo! Descanso logo ali! 🛋️",
    "Trabalhe como um campeão! Descanso de ouro depois! 🏆",
    "Foquemos! Memes depois! 🤓",
    "Produtividade agora, relaxamento depois! 🍹",
    "Vamos focar! O descanso está próximo! 💤",
    "Concentração máxima agora! Preguiça depois! 🦥",
    "Trabalho pesado agora! Meme leve depois! 💪",
    "Vamos focar! A folga vem aí! 🏖️",
    "Produtividade alta! Relaxamento em breve! 📚",
    "Trabalho duro agora! Relax total depois! 😎",
    "Foquemos agora! Preguiça depois! 🦥",
    "Concentração total! Relax depois! 🌟",
    "Produtividade máxima agora! Netflix depois! 🍿",
    "Hora de focar! O descanso vem logo! 🧘‍♀️",
    "Concentração total agora! Memes depois! 😂",
    "Trabalho agora! Relaxamento depois! 🛋️",
    "Foquemos agora! Descanso em breve! ⏳",
    "Trabalhe duro! Descanso mole! 😴",
    "Produtividade agora! Descanso logo ali! 🏖️",
    "Vamos focar! Relaxamento depois! 🌟",
    "Foquemos! O descanso vem rápido! 🏖️"
)

val restPhrases = arrayOf(
    "Agora descanse, porque daqui a pouco tem que voltar para o foco! 😴",
    "Hora de relaxar! A produtividade volta logo! 🛋️",
    "Relaxe agora! Memes depois! 😂",
    "Descanse como um rei! Trabalho depois! 👑",
    "Relax total! Concentração em breve! 🧘‍♂️",
    "Hora de descansar! Foco logo ali! 📚",
    "Descanse! O trabalho vem depois! 🛌",
    "Relax agora! Produtividade em breve! 🧘‍♀️",
    "Hora de folgar! O trabalho espera! 🛋️",
    "Preguiça agora! Foco depois! 🦥",
    "Descanse como um leão! Trabalho depois! 🦁",
    "Relaxe total! Concentração máxima depois! 🧘‍♂️",
    "Hora de folgar! Foco logo ali! 📚",
    "Relax agora! Produtividade em breve! 🌈",
    "Descanso total! Concentração depois! ⚡",
    "Hora de relaxar! Trabalho depois! 📚",
    "Descanse como um rei! Concentração depois! 👑",
    "Relax total! Produtividade em breve! 🧘‍♂️",
    "Hora de folgar! Trabalho espera! 🛋️",
    "Preguiça agora! Concentração depois! 🦥",
    "Descanse como um leão! Foco depois! 🦁",
    "Relaxe total! Produtividade máxima depois! 🧘‍♀️",
    "Hora de folgar! Foco logo ali! 📚",
    "Relax agora! Trabalho em breve! 📱",
    "Descanso total! Produtividade depois! 🦥",
    "Hora de relaxar! Trabalho espera! 🛌",
    "Descanse! Concentração em breve! 🧘‍♂️",
    "Relax agora! Produtividade logo ali! 🌈",
    "Hora de folgar! Foco em breve! 📚",
    "Descanso agora! Trabalho depois! 🛋️",
    "Preguiça total! Concentração depois! 🦥",
    "Descanse como um rei! Foco depois! 👑",
    "Relaxe total! Trabalho em breve! 📱",
    "Hora de relaxar! Produtividade logo ali! 🧘‍♂️",
    "Descanse agora! Foco depois! 📚",
    "Relax agora! Concentração depois! ⚡",
    "Descanso total! Produtividade logo ali! 🦥",
    "Hora de folgar! Trabalho em breve! 🛋️",
    "Preguiça total! Concentração depois! 👑",
    "Descanse como um leão! Produtividade depois! 🦁",
    "Relaxe total! Trabalho em breve! 🌈",
    "Hora de relaxar! Foco logo ali! 🧘‍♂️",
    "Descanse agora! Produtividade depois! 📚",
    "Relax agora! Trabalho logo ali! 🦥",
    "Descanso total! Concentração logo ali! 🛋️",
    "Hora de folgar! Trabalho logo ali! 📚",
    "Preguiça total! Foco logo ali! 🦥",
    "Descanse como um leão! Concentração depois! 🦁",
    "Relaxe total! Produtividade logo ali! 🧘‍♀️"
)

fun getRandomPhrase(phrases: Array<String>): String {
    return phrases.random()
}

@Composable
fun CartaoStatus(
    modifier: Modifier = Modifier,
    isResting: Boolean = false
) {
    val phrase by rememberUpdatedState(getRandomPhrase(if (isResting) restPhrases else workPhrases))

    Card(
        colors = CardDefaults.cardColors(
            containerColor = when (isResting) {
                true -> tertiaryContainerLight
                false -> inversePrimaryLightMediumContrast
            }
        ),
        modifier = Modifier.padding(start = 32.dp, end = 32.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = when (isResting) {
                    true -> "Descansa"
                    false -> "Foca! mah"
                },
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = phrase,
                modifier = Modifier.padding(top = 8.dp), // Adjust padding
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Preview(name = "CartaoStatus")
@Composable
private fun PreviewCartaoStatus() {
    CartaoStatus()
}