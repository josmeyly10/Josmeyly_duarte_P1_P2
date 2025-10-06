package com.example.josmeyly_duarte_p1_p2.Presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.josmeyly_duarte_p1_p2.domain.model.Huacales

@Composable
fun ListHuacalesScreen(
    viewModel: ListHuacalesViewModel = hiltViewModel(),
    onNavigateToEdit: (Int) -> Unit,
    onNavigateToCreate: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    // 🔹 Detectar eventos de navegación
    state.navigateToEditId?.let { id ->
        onNavigateToEdit(id)
        viewModel.onNavigationHandled()
    }

    if (state.navigationToCreate) {
        onNavigateToCreate()
        viewModel.onNavigationHandled()
    }

    ListHuacalesBody(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun ListHuacalesBody(
    state: ListHuacalesUiState,
    onEvent: (ListHuacalesUiEvent) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .testTag("loading")
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .testTag("huacales_list")
        ) {
            items(state.huacales) { huacal ->
                HuacalesCard(
                    huacales = huacal,
                    onEdit = { onEvent(ListHuacalesUiEvent.Edit(huacal.IdEntrada)) },
                    onDelete = { onEvent(ListHuacalesUiEvent.Delete(huacal.IdEntrada)) }
                )
            }
        }

        // 🔹 Botón flotante para crear nuevo huacal
        FloatingActionButton(
            onClick = { onEvent(ListHuacalesUiEvent.CreateNew) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text("+")
        }
    }
}

@Composable
fun HuacalesCard(
    huacales: Huacales,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .testTag("huacales_card_${huacales.IdEntrada}")
            .clickable { onEdit() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(huacales.NombreCliente, style = MaterialTheme.typography.titleMedium)
                Text("Cantidad: ${huacales.Cantidad}")
            }
            TextButton(onClick = onEdit) { Text("Editar") }
            TextButton(onClick = onDelete) { Text("Eliminar") }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListHuacalesBodyPreview() {
    val state = ListHuacalesUiState(
        huacales = listOf(
            Huacales(IdEntrada = 1, NombreCliente = "Juan", Cantidad = 5, Fecha = "2025-10-06", Precio = 100),
            Huacales(IdEntrada = 2, NombreCliente = "Maria", Cantidad = 10, Fecha = "2025-10-06", Precio = 200)
        ),
        isLoading = false
    )
    MaterialTheme {
        ListHuacalesBody(state) {}
    }
}