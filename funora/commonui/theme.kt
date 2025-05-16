// common-ui/src/main/java/com/pb/funora/commonui/Theme.kt
package com.pb.funora.commonui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color // Color sınıfını import etmeniz gerekebilir

private val LightColors = lightColorScheme(
    primary = Color(0xFF6200EE), // Örnek birincil renk, istediğiniz renkle değiştirin
    secondary = Color(0xFF03DAC5) // Örnek ikincil renk, istediğiniz renkle değiştirin
)

@Composable
fun Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        content = content
    )
}
