package com.example.josmeyly_duarte_p1_p2.Presentation.edit

data class EditHuacalesUiState(
    val IdEntrada: Int? = null,
    val nombrecliente: String = "",
    val nombreclienteError: String? = null,
    val cantidad: String = "",
    val cantidadError: String? = null,
    val fecha: String = "",
    val fechaError: String? = null,
    val precio: String = "",
    val precioError: String? = null,
    val isNew: Boolean = true,
    val isSaving: Boolean = false,
    val isDeleting: Boolean = false,
    val deleted: Boolean = false
)


