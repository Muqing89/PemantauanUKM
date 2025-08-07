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
import com.example.pemantauanukm.data.local.database.KegiatanEntity
import com.example.pemantauanukm.viewmodel.KegiatanViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TambahKegiatanScreen(navController: NavController, viewModel: KegiatanViewModel) {
    var nama by remember { mutableStateOf("") }
    var tanggal by remember { mutableStateOf("") }
    var deskripsi by remember { mutableStateOf("") }
    var anggaranText by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Tambah Kegiatan") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
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
                placeholder = { Text("yyyy-mm-dd") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = deskripsi,
                onValueChange = { deskripsi = it },
                label = { Text("Deskripsi") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = anggaranText,
                onValueChange = { anggaranText = it },
                label = { Text("Anggaran") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(onClick = { launcher.launch("image/*") }) {
                Text("Pilih Gambar")
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
                    val anggaran = anggaranText.toDoubleOrNull() ?: 0.0
                    val kegiatanEntity = KegiatanEntity(
                        nama = nama,
                        tanggal = tanggal,
                        deskripsi = deskripsi,
                        anggaran = anggaran,
                        fotoUrl = imageUri?.toString() ?: ""
                    )
                    viewModel.insertKegiatan(kegiatanEntity)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Simpan")
            }
        }
    }
}
