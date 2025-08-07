package com.example.pemantauanukm.screen.kegiatan

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.pemantauanukm.viewmodel.KegiatanViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditKegiatanScreen(
    navController: NavController,
    kegiatanId: Int,
    viewModel: KegiatanViewModel
) {
    val kegiatan = viewModel.kegiatanList.collectAsState().value.find { it.id == kegiatanId }

    var nama by remember { mutableStateOf("") }
    var tanggal by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }
    var anggaran by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    LaunchedEffect(kegiatan) {
        kegiatan?.let {
            nama = it.nama
            tanggal = it.tanggal
            deskripsi = it.deskripsi
            anggaran = it.anggaran.toString()
            imageUri = if (it.fotoUrl.isNotEmpty()) Uri.parse(it.fotoUrl) else null
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Edit Kegiatan") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = nama,
                onValueChange = { nama = it },
                label = { Text("Nama Kegiatan") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = tanggal,
                onValueChange = { tanggal = it },
                label = { Text("Tanggal") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = deskripsi,
                onValueChange = { deskripsi = it },
                label = { Text("Deskripsi") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = anggaran,
                onValueChange = { anggaran = it },
                label = { Text("Anggaran") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(onClick = { imagePicker.launch("image/*") }) {
                Text("Pilih Gambar Baru")
            }

            imageUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = "Foto Kegiatan",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Button(
                onClick = {
                    kegiatan?.let {
                        val updated = it.copy(
                            nama = nama,
                            tanggal = tanggal,
                            deskripsi = deskripsi,
                            anggaran = anggaran.toDoubleOrNull() ?: 0.0,
                            fotoUrl = imageUri?.toString() ?: ""
                        )
                        viewModel.updateKegiatan(updated)
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Simpan Perubahan")
            }
        }
    }
}
