package com.tuapp.usuariosapi

import com.tuapp.usuariosapi.modelo.Usuario
import retrofit2.http.GET

/**
 * INTERFAZ: ApiService
 * Define los endpoints de la API que Retrofit consumirá.
 */
interface ApiService {
    // PRUEBA CAMBIANDO ESTO:
    // Si en MockAPI tu recurso se llama "usuarios", cámbialo aquí.
    @GET("usuarios")
    suspend fun obtenerListaUsuarios(): List<Usuario>
}
