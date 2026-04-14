package com.tuapp.usuariosapi.red

import com.tuapp.usuariosapi.modelo.Usuario
import retrofit2.http.GET

interface ApiService {
    // REVISIÓN: El nombre debe ser EXACTO al de MockAPI.
    // Prueba con este nombre que es el que tenías originalmente:
    @GET("usaruario-contrase-id")
    suspend fun obtenerListaUsuarios(): List<Usuario>
}
