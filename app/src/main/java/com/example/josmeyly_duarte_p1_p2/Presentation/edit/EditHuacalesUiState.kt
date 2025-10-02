package com.example.josmeyly_duarte_p1_p2.Presentation.edit

data class EditHuacalesUiState(
    val id: Int? = null,
    val nombrecliente: String = "",
    val cantidad: String = "",
    val nombreclienteError: String? = null,
    val cantidadError: String? = null,
    val isSaving: Boolean = false
)


