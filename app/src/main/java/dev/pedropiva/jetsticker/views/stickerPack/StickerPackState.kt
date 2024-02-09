package dev.pedropiva.jetsticker.views.stickerPack

import android.media.Image
import android.net.Uri

data class StickerPackState(
    val images: List<Uri> = emptyList()
)