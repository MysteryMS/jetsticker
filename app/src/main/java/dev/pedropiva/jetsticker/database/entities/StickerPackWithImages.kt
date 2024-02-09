package dev.pedropiva.jetsticker.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class StickerPackWithImages(
    @Embedded val pack: StickerPack,
    @Relation(
        parentColumn = "id",
        entityColumn = "packId"
    )
    val images: List<StickerImage>
)
