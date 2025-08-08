package com.example.pemantauanukm.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pemantauanukm.data.local.database.AppDatabase
import com.example.pemantauanukm.data.local.database.KegiatanRepository
import com.example.pemantauanukm.screen.dashboard.DashboardOrmawa
import com.example.pemantauanukm.screen.kegiatan.*
import com.example.pemantauanukm.screen.login.LoginScreen
import com.example.pemantauanukm.screen.login.RegisterScreen
import com.example.pemantauanukm.utils.SharedPrefManager
import com.example.pemantauanukm.viewmodel.KegiatanViewModel
import com.example.pemantauanukm.viewmodel.KegiatanViewModelFactory

@Composable
fun AppNavigation(
    navController: NavHostController,
    database: AppDatabase,
    sharedPrefManager: SharedPrefManager
) {
    val repository = KegiatanRepository(database.kegiatanDao())
    val viewModel: KegiatanViewModel = viewModel(factory = KegiatanViewModelFactory(repository))

    NavHost(navController = navController, startDestination = Routes.LOGIN) {

        composable(Routes.LOGIN) {
            val context = LocalContext.current
            val sharedPref = remember { SharedPrefManager(context) }
            LoginScreen(navController, sharedPref)
        }

        composable(Routes.REGISTER) {
            val context = LocalContext.current
            val sharedPref = remember { SharedPrefManager(context) }
            RegisterScreen(navController, sharedPref)
        }

        composable(Routes.DASHBOARD_ORMAWA) {
            DashboardOrmawa(navController, viewModel, sharedPrefManager)
        }

        composable(Routes.KEGIATAN_LIST) {
            KegiatanListScreen(navController, viewModel)
        }

        composable(Routes.TAMBAH_KEGIATAN) {
            TambahKegiatanScreen(navController, viewModel)
        }

        composable(Routes.EDIT_KEGIATAN) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
            EditKegiatanScreen(navController, id, viewModel)
        }

        composable(Routes.DETAIL_KEGIATAN) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
            DetailKegiatanScreen(navController, id, viewModel)
        }
    }
}
