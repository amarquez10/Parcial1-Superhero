package com.example.parcial1movilsuperheroe.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.parcial1movilsuperheroe.model.Superhero
import com.example.parcial1movilsuperheroe.ui.SuperheroDetailUiState
import com.example.parcial1movilsuperheroe.ui.SuperheroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    heroId: Int,
    viewModel: SuperheroViewModel,
    onBackClick: () -> Unit
) {
    LaunchedEffect(heroId) {
        viewModel.fetchSuperheroById(heroId)
    }

    val uiState by viewModel.detailUiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Héroe") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                    }
                },
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when (val state = uiState) {
                is SuperheroDetailUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is SuperheroDetailUiState.Success -> {
                    DetailContent(hero = state.hero)
                }
                is SuperheroDetailUiState.Error -> {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
fun DetailContent(hero: Superhero) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Hero Image
        AsyncImage(
            model = hero.imageUrlLg,
            contentDescription = hero.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.padding(16.dp)) {
            // Header
            Text(
                text = hero.name,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = hero.fullName,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Info Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Información General", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    Text("Editorial: ${hero.publisher}")
                    Text("Lugar de nacimiento: ${hero.placeOfBirth}")
                    Text("Primera aparición: ${hero.firstAppearance}")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Stats
            Text("Estadísticas", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            StatBar("Inteligencia", hero.intelligence, Color(0xFF4CAF50))
            StatBar("Fuerza", hero.strength, Color(0xFFF44336))
            StatBar("Velocidad", hero.speed, Color(0xFFFF9800))
            StatBar("Durabilidad", hero.durability, Color(0xFF9E9E9E))
            StatBar("Poder", hero.power, Color(0xFF9C27B0))
            StatBar("Combate", hero.combat, Color(0xFF3F51B5))
        }
    }
}

@Composable
fun StatBar(label: String, value: Int, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(0.4f),
            fontSize = 14.sp
        )
        Box(
            modifier = Modifier
                .weight(0.6f)
                .height(12.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color.LightGray)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(value / 100f)
                    .height(12.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(color)
            )
        }
        Text(
            text = value.toString(),
            modifier = Modifier.padding(start = 8.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
