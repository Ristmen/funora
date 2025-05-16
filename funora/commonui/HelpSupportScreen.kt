// HelpSupportScreen.kt
package com.pb.funora.commonui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem as Material3ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pb.funora.commonui.model.FaqItem

@Composable
fun HelpSupportScreen(
    faqs: List<FaqItem>,
    onSendFeedback: (String) -> Unit,
    onOpenPolicy: (String) -> Unit,
    onBack: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        state = rememberLazyListState()
    ) {
        items(faqs) { faq ->
            Material3ListItem(
                headlineContent = {
                    Text(text = faq.question, style = MaterialTheme.typography.titleMedium)
                },
                supportingContent = {
                    Text(text = faq.answer, style = MaterialTheme.typography.bodyMedium)
                },
                trailingContent = {
                    IconButton(onClick = { /* Detay aç */ }) {
                        Icon(
                            imageVector = Icons.Default.ChevronRight,
                            contentDescription = "Detay"
                        )
                    }
                }
            )
        }
    }
}
