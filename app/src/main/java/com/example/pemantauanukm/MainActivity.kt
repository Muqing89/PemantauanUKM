package com.example.pemantauanukm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.pemantauanukm.data.local.database.AppDatabase
import com.example.pemantauanukm.navigation.AppNavigation
import com.example.pemantauanukm.theme.PemantauanUKMTheme
import com.example.pemantauanukm.utils.SharedPrefManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)
        val sharedPrefManager = SharedPrefManager(applicationContext)

        setContent {
            PemantauanUKMTheme {
                val navController = rememberNavController()

                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(
                        navController = navController,
                        database = database,
                        sharedPrefManager = sharedPrefManager
                    )
                }
            }
        }
    }
}
