package com.tuapp.usuariosapi.red

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // URL raíz: Ahora la dejamos solo hasta ".io/" para que el endpoint sea el path completo.
    private const val URL_BASE = "https://69de374a410caa3d47baccd4.mockapi.io/"

    val servicioApi: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
