package com.example.pemantauanukm.screen.kegiatan

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pemantauanukm.viewmodel.KegiatanViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailKegiatanScreen(navController: NavController, id: Int, viewModel: KegiatanViewModel) {
    val kegiatanState = viewModel.getKegiatanById(id).collectAsState(initial = null)
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Kegiatan") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            kegiatanState.value?.let { data ->
                Text("Nama Kegiatan", style = MaterialTheme.typography.labelMedium)
                Text(data.nama, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(12.dp))

                Text("Tanggal", style = MaterialTheme.typography.labelMedium)
                Text(data.tanggal, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(12.dp))

                Text("Deskripsi", style = MaterialTheme.typography.labelMedium)
                Text(data.deskripsi, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(12.dp))

                Text("Anggaran", style = MaterialTheme.typography.labelMedium)
                Text(data.anggaran.toString(), style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(12.dp))

                Text("Foto", style = MaterialTheme.typography.labelMedium)
                AsyncImage(
                    model = data.fotoUrl,
                    contentDescription = "Foto Kegiatan",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { showDialog = true },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Hapus Kegiatan", color = Color.White)
                }

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        confirmButton = {
                            TextButton(onClick = {
                                viewModel.deleteKegiatan(data)
                                showDialog = false
                                navController.popBackStack()
                            }) {
                                Text("Ya")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDialog = false }) {
                                Text("Batal")
                            }
                        },
                        title = { Text("Konfirmasi Hapus") },
                        text = { Text("Apakah Anda yakin ingin menghapus kegiatan ini?") }
                    )
                }

            } ?: run {
                Text("Memuat data...", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
