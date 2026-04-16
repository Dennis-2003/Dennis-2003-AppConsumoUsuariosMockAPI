package com.tuapp.usuariosapi.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
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
import kotlinx.coroutines.launch
import java.net.UnknownHostException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaUsuarios() {
    var listaDeUsuarios by remember { mutableStateOf<List<Usuario>>(emptyList()) }
    var cargandoAlPrincipio by remember { mutableStateOf(true) }
    var actualizandoAhora by remember { mutableStateOf(false) }
    var huboError by remember { mutableStateOf<String?>(null) }
    
    val alcanceCorrutina = rememberCoroutineScope()

    fun traerDatosDesdeInternet() {
        alcanceCorrutina.launch {
            huboError = null
            try {
                val respuesta = RetrofitClient.servicioApi.obtenerListaUsuarios()
                listaDeUsuarios = respuesta
            } catch (e: UnknownHostException) {
                // Capturamos específicamente el error de "no hay internet"
                huboError = "Parece que no tienes internet. Revisa tu conexión."
            } catch (e: Exception) {
                huboError = "Algo salió mal: ${e.message}"
            } finally {
                cargandoAlPrincipio = false
                actualizandoAhora = false
            }
        }
    }

    LaunchedEffect(Unit) {
        traerDatosDesdeInternet()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Usuarios", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { relleno ->
        PullToRefreshBox(
            isRefreshing = actualizandoAhora,
            onRefresh = {
                actualizandoAhora = true
                traerDatosDesdeInternet()
            },
            modifier = Modifier.padding(relleno).fillMaxSize()
        ) {
            when {
                cargandoAlPrincipio -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                huboError != null -> {
                    Column(
                        Modifier.fillMaxSize().padding(16.dp), 
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = huboError!!, color = Color.Red, modifier = Modifier.padding(bottom = 16.dp))
                        Button(onClick = { 
                            cargandoAlPrincipio = true
                            traerDatosDesdeInternet() 
                        }) {
                            Text("Reintentar")
                        }
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(listaDeUsuarios) { usuario ->
                            FilaDeUsuario(usuario = usuario)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FilaDeUsuario(usuario: Usuario) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = usuario.avatar ?: "https://via.placeholder.com/150",
                contentDescription = null,
                modifier = Modifier.size(50.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(16.dp))
            Column {
                Text(
                    text = usuario.nombre ?: "Sin nombre",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = usuario.email ?: "Sin correo",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
