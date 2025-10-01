package com.example.josmeyly_duarte_p1_p2.Presentation.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun EditHuacalesScreen(
    viewModel: EditHuacalesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    EditHuacalesBody(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun EditHuacalesBody(
    state: EditHuacalesUiState,
    onEvent: (EditHuacalesUiEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = state.nombrecliente,
            onValueChange = { onEvent(EditHuacalesUiEvent.NombreClienteChanged(it)) },
            label = { Text("Nombres") },
            isError = state.nombreclienteError != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (state.nombreclienteError != null) {
            Text(
                text = state.nombreclienteError,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = state.cantidad,
            onValueChange = { onEvent(EditHuacalesUiEvent.CantidadChanged(it)) },
            label = { Text("Cantidad") },
            isError = state.cantidadError != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (state.cantidadError != null) {
            Text(
                text = state.cantidadError,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { onEvent(EditHuacalesUiEvent.Save) },
            enabled = !state.isSaving,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (state.isSaving) "Guardando..." else "Guardar")
        }
    }
}
@Composable
private fun EditHuacalesBodyPreview() {
    val state = EditHuacalesUiState()
    MaterialTheme {
        EditHuacalesBody(state = state) { }
    }
}