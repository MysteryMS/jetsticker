package dev.pedropiva.jetsticker.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sticker_image")
data class StickerImage(
    @PrimaryKey(true)
    val id: Int = 0,

    @ColumnInfo("image", typeAffinity = ColumnInfo.BLOB)
    val image: ByteArray,
    val packId: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StickerImage

        if (id != other.id) return false
        if (!image.contentEquals(other.image)) return false
        return packId == other.packId
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + image.contentHashCode()
        result = 31 * result + packId
        return result
    }
}
