package com.example.josmeyly_duarte_p1_p2.Presentation.edit

sealed class EditHuacalesUiEvent {
    data class Load(val id: Int?) : EditHuacalesUiEvent()
    data class NombreClienteChanged(val value: String) : EditHuacalesUiEvent()
    data class CantidadChanged(val value: String) : EditHuacalesUiEvent()
    data class FechaChanged(val value: String) : EditHuacalesUiEvent()
    data class PrecioChanged(val value: String) : EditHuacalesUiEvent()

    data class Edit(val id: Int) : EditHuacalesUiEvent()
    object Save : EditHuacalesUiEvent()
    object Delete : EditHuacalesUiEvent()

}