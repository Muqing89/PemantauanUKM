package com.example.pemantauanukm.screen.login

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pemantauanukm.navigation.Routes
import com.example.pemantauanukm.utils.SharedPrefManager
import com.example.pemantauanukm.viewmodel.LoginViewModel
import com.example.pemantauanukm.viewmodel.LoginViewModelFactory

@Composable
fun LoginScreen(
    navController: NavController,
    sharedPrefManager: SharedPrefManager,
    loginViewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory(sharedPrefManager))
) {
    val context = LocalContext.current

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginState by loginViewModel.loginState.collectAsState()

    LaunchedEffect(loginState) {
        loginState?.let {
            if (it) {
                navController.navigate(Routes.DASHBOARD_ORMAWA) {
                    popUpTo(Routes.LOGIN) { inclusive = true }
                }
            } else {
                Toast.makeText(context, "Username atau password salah", Toast.LENGTH_SHORT).show()
                loginViewModel.resetLoginState() // <- penting agar Toast bisa muncul lagi di login berikutnya
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                loginViewModel.login(username.trim(), password.trim())
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        TextButton(onClick = {
            navController.navigate(Routes.REGISTER)
        }) {
            Text("Belum punya akun? Daftar")
        }
    }
}
