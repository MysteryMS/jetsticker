package dev.pedropiva.jetsticker.views.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dev.pedropiva.jetsticker.database.StickerPackDatabase
import dev.pedropiva.jetsticker.database.entities.StickerPack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val db = StickerPackDatabase.getDatabase(application)
    private val dao = db.stickerPackDao()

    fun createPack(name: String, author: String) {
        val stickerPack = StickerPack(0, name, author)
        viewModelScope.launch {
            dao.insertAll(stickerPack)
        }
    }

    init {
        viewModelScope.launch {
            val packs = dao.getAllWithImages()
            _state.update {
                it.copy(stickerPacks = packs)
            }
        }
    }
}