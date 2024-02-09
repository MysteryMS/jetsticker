package dev.pedropiva.jetsticker.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import dev.pedropiva.jetsticker.database.entities.StickerPackWithImages
import dev.pedropiva.jetsticker.database.entities.StickerPack

@Dao
interface StickerPackDao {
    @Query("select * from sticker_pack")
    suspend fun getAll(): List<StickerPack>

    @Transaction
    @Query("select * from sticker_pack")
    suspend fun getAllWithImages(): List<StickerPackWithImages>

    @Insert
    suspend fun insertAll(vararg packs: StickerPack)

    @Delete
    suspend fun deletePack(pack: StickerPack)

    @Query("select * from sticker_pack where pack_name like :name")
    suspend fun findByName(name: String): StickerPack
}