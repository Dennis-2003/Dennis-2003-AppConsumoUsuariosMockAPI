package com.tuapp.usuariosapi.modelo

import com.google.gson.annotations.SerializedName

data class Usuario(
    @SerializedName("id")
    val id: String?,

    // Usamos "Nombre" con N mayúscula porque así viene en tu JSON
    @SerializedName("Nombre")
    val nombre: String?,

    // Añadimos el email que también viene en tus datos
    @SerializedName("email")
    val email: String?,

    @SerializedName("avatar")
    val avatar: String?,

    @SerializedName("createdAt")
    val fechaCreacion: String?
)
