package com.example.parcial1movilsuperheroe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.parcial1movilsuperheroe.ui.navigation.AppNavigation
import com.example.parcial1movilsuperheroe.ui.theme.Parcial1MovilSuperHeroeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Parcial1MovilSuperHeroeTheme {
                AppNavigation()
            }
        }
    }
}