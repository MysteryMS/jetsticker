package dev.pedropiva.jetsticker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.pedropiva.jetsticker.database.dao.StickerPackDao
import dev.pedropiva.jetsticker.database.entities.StickerImage
import dev.pedropiva.jetsticker.database.entities.StickerPack

@Database(entities = [StickerPack::class, StickerImage::class], version = 4)
abstract class StickerPackDatabase : RoomDatabase() {
    abstract fun stickerPackDao(): StickerPackDao

    companion object {
        @Volatile
        private var Instance: StickerPackDatabase? = null

        fun getDatabase(context: Context): StickerPackDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, StickerPackDatabase::class.java, "sticker_packs")
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}