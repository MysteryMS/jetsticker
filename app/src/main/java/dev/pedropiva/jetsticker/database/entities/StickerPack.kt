package dev.pedropiva.jetsticker.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sticker_pack")
data class StickerPack(
    @PrimaryKey(true)
    val id: Int = 0,

    @ColumnInfo(name = "pack_name")
    val packName: String,

    @ColumnInfo(name = "pack_author")
    val packAuthor: String,

    @ColumnInfo(name = "tray_icon", typeAffinity = ColumnInfo.BLOB)
    val trayIcon: ByteArray? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StickerPack

        if (id != other.id) return false
        if (packName != other.packName) return false
        if (packAuthor != other.packAuthor) return false
        if (trayIcon != null) {
            if (other.trayIcon == null) return false
            if (!trayIcon.contentEquals(other.trayIcon)) return false
        } else if (other.trayIcon != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + packName.hashCode()
        result = 31 * result + packAuthor.hashCode()
        result = 31 * result + (trayIcon?.contentHashCode() ?: 0)
        return result
    }
}