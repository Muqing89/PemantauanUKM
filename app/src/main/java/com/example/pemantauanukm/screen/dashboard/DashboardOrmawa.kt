package com.example.pemantauanukm.screen.dashboard

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pemantauanukm.navigation.Routes
import com.example.pemantauanukm.utils.SharedPrefManager
import com.example.pemantauanukm.viewmodel.KegiatanViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardOrmawa(
    navController: NavController,
    viewModel: KegiatanViewModel,
    sharedPrefManager: SharedPrefManager
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard") },
                actions = {
                    TextButton(onClick = {
                        val user = sharedPrefManager.getLoggedInUser()
                        if (user != null) {
                            sharedPrefManager.logout()
                            Toast.makeText(context, "Berhasil logout", Toast.LENGTH_SHORT).show()
                            navController.navigate(Routes.LOGIN) {
                                popUpTo(Routes.DASHBOARD_ORMAWA) { inclusive = true }
                            }
                        } else {
                            Toast.makeText(context, "Akun tidak ditemukan", Toast.LENGTH_SHORT).show()
                            navController.navigate(Routes.REGISTER) {
                                popUpTo(Routes.DASHBOARD_ORMAWA) { inclusive = true }
                            }
                        }
                    }) {
                        Text("Logout", color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { navController.navigate(Routes.KEGIATAN_LIST) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Lihat Daftar Kegiatan")
            }

            Button(
                onClick = { navController.navigate(Routes.TAMBAH_KEGIATAN) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Tambah Kegiatan")
            }
        }
    }
}
