package com.example.pemantauanukm.screen.kegiatan

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pemantauanukm.data.local.database.KegiatanEntity
import com.example.pemantauanukm.navigation.Routes
import com.example.pemantauanukm.viewmodel.KegiatanViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KegiatanListScreen(navController: NavController, viewModel: KegiatanViewModel) {
    val kegiatanList = viewModel.kegiatanList.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Daftar Kegiatan") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Routes.TAMBAH_KEGIATAN)
            }) {
                Text("+")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(kegiatanList.value) { kegiatan ->
                KegiatanItem(kegiatanEntity = kegiatan, navController = navController)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun KegiatanItem(kegiatanEntity: KegiatanEntity, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(Routes.detailKegiatan(kegiatanEntity.id))
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = kegiatanEntity.nama, style = MaterialTheme.typography.titleMedium)
            Text(text = kegiatanEntity.tanggal, style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(onClick = {
                navController.navigate(Routes.editKegiatan(kegiatanEntity.id))
            }) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
                Spacer(modifier = Modifier.width(4.dp))
                Text("Edit")
            }
        }
    }
}
