package com.example.josmeyly_duarte_p1_p2.Presentation.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.example.josmeyly_duarte_p1_p2.domain.model.Huacales
import com.example.josmeyly_duarte_p1_p2.domain.usecase.DeleteHuacalesUseCase
import com.example.josmeyly_duarte_p1_p2.domain.usecase.ExisteHuacalesUseCase
import com.example.josmeyly_duarte_p1_p2.domain.usecase.GetHuacalesUseCase
import com.example.josmeyly_duarte_p1_p2.domain.usecase.UpsertHuacalesUseCase
import com.example.josmeyly_duarte_p1_p2.domain.usecase.validateCantidad
import com.example.josmeyly_duarte_p1_p2.domain.usecase.validateNombreCliente
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
    private val _state = MutableStateFlow(value = EditHuacalesUiState())

    val state: StateFlow<EditHuacalesUiState> = _state.asStateFlow()

    fun onEvent(event: EditHuacalesUiEvent) {
        when (event) {
            is EditHuacalesUiEvent.Load -> onLoad(id = event.id)
            is EditHuacalesUiEvent.NombreClienteChanged -> _state.update {
                it.copy(nombrecliente = event.value, nombreError = null)
            }

            is EditHuacalesUiEvent.CantidadChanged -> _state.update {
                it.copy(cantidad = event.value, cantidadError = null)
            }

            EditHuacalesUiEvent.Save -> onSave()
            EditHuacalesUiEvent.Delete -> onDelete()
        }
    }

    private fun onLoad(id: Int?) {
        if (id == null || id == 0) {
            _state.update { it.copy(isNew = true, jugadorId = null) }
            return
        }
        viewModelScope.launch {
            val huacales = getHuacalesUseCase(id)
            if (huacales != null) {
                _state.update {
                    it.copy(
                        isNew = false,
                        IdEntrada= huacales.IdEntrada,
                        nombrecliente = huacales.NombreCliennte,
                        cantidad = huacales.Cantidad.toString()
                    )
                }
            }
        }
    }

    private fun onSave() {
        val nombrecliente = state.value.nombrecliente
        val nombresValidations = validateNombreCliente(nombrecliente)
        val cantidad = state.value.cantidad
        val p = validateCantidad(cantidad)

        if (!nombresValidations.isValid || !p.isValid) {
            _state.update {
                it.copy(
                    nombreclienteError = nombreclienteValidations.error,
                    cantidadError = p.error
                )
            }
            return
        }

        viewModelScope.launch {
            val currentId = state.value.IdEntrada
            if (existeNombreClienteUseCase(nombrecliente, currentId)) {
                _state.update {
                    it.copy(
                        nombreError = "Ya existe un cliente con ese nombre"
                    )
                }
                return@launch
            }

            _state.update { it.copy(isSaving = true) }
            val id = state.value.IdEntrada ?: 0
            val huacales = Huacales(
                IdEntrada = id,
                NombreCliente = nombrecliente,
                Cantidad = cantidad.toInt()
            )
            val result = upsertHuacalesUseCase(huacales)
            result.onSuccess { newId ->
                _state.value = EditHuacalesUiState()
            }.onFailure { e ->
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