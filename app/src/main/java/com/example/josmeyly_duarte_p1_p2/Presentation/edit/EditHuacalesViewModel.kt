package com.example.josmeyly_duarte_p1_p2.Presentation.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.josmeyly_duarte_p1_p2.domain.model.Huacales
import com.example.josmeyly_duarte_p1_p2.domain.usecase.DeleteHuacalesUseCase
import com.example.josmeyly_duarte_p1_p2.domain.usecase.ExisteHuacalesUseCase
import com.example.josmeyly_duarte_p1_p2.domain.usecase.GetHuacalesUseCase
import com.example.josmeyly_duarte_p1_p2.domain.usecase.UpsertHuacalesUseCase
import com.example.josmeyly_duarte_p1_p2.domain.usecase.validateCantidad
import com.example.josmeyly_duarte_p1_p2.domain.usecase.validateFecha
import com.example.josmeyly_duarte_p1_p2.domain.usecase.validateNombreCliente
import com.example.josmeyly_duarte_p1_p2.domain.usecase.validatePrecio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditHuacalesViewModel @Inject constructor(
    private val getHuacalesUseCase: GetHuacalesUseCase,
    private val upsertHuacalesUseCase: UpsertHuacalesUseCase,
    private val deleteHuacalesUseCase: DeleteHuacalesUseCase,
    private val existeHuacalesUseCase: ExisteHuacalesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(EditHuacalesUiState())
    val state: StateFlow<EditHuacalesUiState> = _state.asStateFlow()

    fun onEvent(event: EditHuacalesUiEvent) {
        when (event) {
            is EditHuacalesUiEvent.Load -> onLoad(event.id)
            is EditHuacalesUiEvent.NombreClienteChanged -> _state.update {
                it.copy(nombrecliente = event.value, nombreclienteError = null)
            }
            is EditHuacalesUiEvent.CantidadChanged -> _state.update {
                it.copy(cantidad = event.value, cantidadError = null)
            }
            is EditHuacalesUiEvent.FechaChanged -> _state.update {
                it.copy(fecha = event.value, fechaError = null)
            }
            is EditHuacalesUiEvent.PrecioChanged -> _state.update {
                it.copy(precio = event.value, precioError = null)
            }
            EditHuacalesUiEvent.Save -> onSave()
            EditHuacalesUiEvent.Delete -> onDelete()
        }
    }

    private fun onLoad(id: Int?) {
        if (id == null || id == 0) {
            _state.update { it.copy(isNew = true, IdEntrada = null) }
            return
        }
        viewModelScope.launch {
            val huacales = getHuacalesUseCase(id)
            if (huacales != null) {
                _state.update {
                    it.copy(
                        isNew = false,
                        IdEntrada = huacales.IdEntrada,
                        nombrecliente = huacales.NombreCliente,
                        cantidad = huacales.Cantidad.toString(),
                        fecha = huacales.Fecha,
                        precio = huacales.Precio.toString()
                    )
                }
            }
        }
    }

    private fun onSave() {
        val nombrecliente = state.value.nombrecliente
        val cantidad = state.value.cantidad
        val fecha = state.value.fecha
        val precio = state.value.precio

        val nombreValidation = validateNombreCliente(nombrecliente)
        val cantidadValidation = validateCantidad(cantidad)
        val fechaValidation = validateFecha(fecha)
        val precioValidation = validatePrecio(precio)


        if (!nombreValidation.isValid ||
            !cantidadValidation.isValid ||
            !fechaValidation.isValid ||
            !precioValidation.isValid
        ) {
            _state.update {
                it.copy(
                    nombreclienteError = nombreValidation.error,
                    cantidadError = cantidadValidation.error,
                    fechaError = fechaValidation.error,
                    precioError = precioValidation.error
                )
            }
            return
        }

        viewModelScope.launch {
            val currentId = state.value.IdEntrada
            if (existeHuacalesUseCase(nombrecliente, currentId)) {
                _state.update {
                    it.copy(nombreclienteError = "Ya existe un cliente con ese nombre")
                }
                return@launch
            }

            _state.update { it.copy(isSaving = true) }

            val huacales = Huacales(
                IdEntrada = currentId ?: 0,
                NombreCliente = nombrecliente,
                Cantidad = cantidad.toInt(),
                Fecha = fecha,
                Precio = precio.toInt(),
            )

            val result = upsertHuacalesUseCase(huacales)

            result.onSuccess {
                _state.value = EditHuacalesUiState()
            }.onFailure {
                _state.update { it.copy(isSaving = false) }
            }
        }
    }

    private fun onDelete() {
        val id = state.value.IdEntrada ?: return
        viewModelScope.launch {
            _state.update { it.copy(isDeleting = true) }
            deleteHuacalesUseCase(id)
            _state.update { it.copy(isDeleting = false, deleted = true) }
        }
    }
}