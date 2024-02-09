package dev.pedropiva.jetsticker.views.stickerPack

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import dev.pedropiva.jetsticker.components.AddImageDashed

@Composable
fun StickerPack(vm: StickerPackViewModel = viewModel()) {
    val state by vm.state.collectAsState()
    val pickMedia =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) {
            if (it != null) {
                vm.addImage(it)
            }
        }

    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(top = 50.dp)
                .padding(horizontal = 16.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                AddImageDashed(showTrayLabel = true) {}
            }
            Spacer(modifier = Modifier.height(50.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(state.images) {
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .border(1.dp, Color.White, RoundedCornerShape(10))
                    ) {
                        AsyncImage(
                            model = it,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.Center)
                        )
                    }
                }
                item {
                    AddImageDashed {
                        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }
                }
            }
        }
    }
}