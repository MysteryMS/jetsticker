package dev.pedropiva.jetsticker.views.stickerPack

import android.app.Application
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.MutableState
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StickerPackViewModel(application: Application) : AndroidViewModel(application) {
    val state = MutableStateFlow(StickerPackState())

    fun addImage(uri: Uri) {
        val mutable = state.value.images.toMutableList()
        mutable.add(uri)
        state.value = state.value.copy(images = mutable.toList())
    }

}