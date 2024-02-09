package dev.pedropiva.jetsticker.views.home

import dev.pedropiva.jetsticker.database.entities.StickerPack
import dev.pedropiva.jetsticker.database.entities.StickerPackWithImages

data class HomeState(
    val stickerPacks: List<StickerPackWithImages> = emptyList()
)
