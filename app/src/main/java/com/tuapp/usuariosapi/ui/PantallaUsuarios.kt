package com.tuapp.usuariosapi.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.tuapp.usuariosapi.modelo.Usuario
import com.tuapp.usuariosapi.red.RetrofitClient

@Composable
fun PantallaUsuarios() {
    var listaUsuarios by remember { mutableStateOf<List<Usuario>>(emptyList()) }
    var estaCargando by remember { mutableStateOf(true) }
    var mensajeError by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            val respuestaApi = RetrofitClient.servicioApi.obtenerListaUsuarios()
            listaUsuarios = respuestaApi
        } catch (excepcion: Exception) {
            mensajeError = "Error al cargar datos: ${excepcion.message}"
        } finally {
            estaCargando = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Lista de Usuarios",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        when {
            estaCargando -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            mensajeError != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = mensajeError!!, color = Color.Red)
                }
            }
            else -> {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(listaUsuarios) { usuario ->
                        TarjetaUsuario(usuario = usuario)
                    }
                }
            }
        }
    }
}

@Composable
fun TarjetaUsuario(usuario: Usuario) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = usuario.avatar ?: "https://via.placeholder.com/150",
                contentDescription = null,
                modifier = Modifier.size(56.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(14.dp))
            Column {
                Text(
                    text = usuario.nombre ?: "Sin nombre",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = usuario.email ?: "Sin email",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = "ID: ${usuario.id}",
                    fontSize = 11.sp,
                    color = Color.LightGray
                )
            }
        }
    }
}
