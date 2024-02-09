package dev.pedropiva.jetsticker.views.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Home(vm: HomeViewModel = viewModel(), onCreate: () -> Unit) {
    val state by vm.state.collectAsState()
    val showCreateDialog = rememberSaveable { mutableStateOf(false) }
    val packTitle = rememberSaveable { mutableStateOf("") }
    val packAuthor = rememberSaveable { mutableStateOf("") }

    if (showCreateDialog.value) {
        Dialog(onDismissRequest = { showCreateDialog.value = false }) {
            Box(
                modifier = Modifier
                    .background(AlertDialogDefaults.containerColor, MaterialTheme.shapes.extraLarge)
                    .padding(24.dp)
            ) {
                Column {
                    Text("Create new pack", style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.padding(16.dp))

                    OutlinedTextField(
                        value = packTitle.value,
                        onValueChange = { packTitle.value = it },
                        label = { Text("Title") }
                    )
                    Spacer(modifier = Modifier.padding(5.dp))

                    OutlinedTextField(
                        value = packAuthor.value,
                        onValueChange = { packAuthor.value = it },
                        label = { Text("Author") }
                    )

                    Spacer(modifier = Modifier.padding(24.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = { showCreateDialog.value = false }) {
                            Text("Cancel")
                        }
                        TextButton(onClick = {
                            showCreateDialog.value = false
                            vm.createPack(packTitle.value, packAuthor.value)
                            onCreate()
                        }) {
                            Text("Create")
                        }
                    }
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            CreateFab {
                showCreateDialog.value = true
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {

            if (state.stickerPacks.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "You don't have any packs yet")
                }
            }
        }
    }
}

@Composable
private fun CreateFab(onClick: () -> Unit) {
    ExtendedFloatingActionButton(onClick = onClick) {
        Icon(imageVector = Icons.Rounded.Add, contentDescription = "")
        Text(text = "New pack")
    }
}
