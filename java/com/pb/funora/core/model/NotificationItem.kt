// NotificationItem.kt
package com.pb.funora.core.model
import androidx.compose.ui.graphics.vector.ImageVector

data class NotificationItem(
    val id: String,
    val title: String,
    val timeAgo: String,
    val icon: ImageVector
)
