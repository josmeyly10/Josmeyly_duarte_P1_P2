package com.example.josmeyly_duarte_p1_p2.Presentation.edit

interface EditHuacalesUiEvent {

    data class Load(val id: Int?) : EditHuacalesUiEvent

    data class NombreClienteChanged(val value: String) : EditHuacalesUiEvent

    data class CantidadChanged(val value: String) : EditHuacalesUiEvent

    data object Save : EditHuacalesUiEvent

    data object Delete : EditHuacalesUiEvent
}