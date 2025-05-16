package com.pb.funora.auth.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.pb.funora.R

/**
 * Sağ üst köşede dil değiştirme ikonu.
 *
 * @param currentLocale Şu anki dil kodu ("tr" veya "en")
 * @param onLocaleChange Kullanıcı ikona tıkladığında yeni dil kodunu bildiren callback
 */
@Composable
fun LanguageChangeIcon(
    currentLocale: String,
    onLocaleChange: (String) -> Unit
) {
    IconButton(onClick = {
        // Burada tr<->en geçiş örneği var, ihtiyacınıza göre genişletebilirsiniz
        val next = if (currentLocale == "tr") "en" else "tr"
        onLocaleChange(next)
    }) {
        Icon(
            imageVector = Icons.Filled.Language,
            contentDescription = stringResource(R.string.language)
        )
    }
}
