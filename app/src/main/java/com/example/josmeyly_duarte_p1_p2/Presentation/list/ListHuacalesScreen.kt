package com.example.josmeyly_duarte_p1_p2.Presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.room.Delete
import com.example.josmeyly_duarte_p1_p2.domain.model.Huacales

@Composable
fun ListHuacalesScreen(
    viewModel: ListHuacalesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ListHuacalesBody(state, viewModel::onEvent)
}

@Composable
fun ListHuacalesBody(
    state: ListHuacalesUiState,
    onEvent: (ListHuacalesUiEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
                .testTag("hucales_list")
        ) {
            items(state.huacales) { huacales ->
                HuacalesCard(
                    huacales= huacales,
                    onEdit = { onEvent(ListHuacalesUiEvent.Edit(huacales.IdEntrada)) },
                    onDelete = { onEvent(ListHuacalesUiEvent.Delete(huacales.IdEntrada)) }
                )
            }
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
            TextButton(
                onClick = onEdit,
                modifier = Modifier.testTag("edit_button_${huacales.IdEntrada}")
            ) {
                Text("Editar")
            }
            TextButton(
                onClick = onDelete,
                modifier = Modifier.testTag("delete_button_${huacales.IdEntrada}")
            ) {
                Text("Eliminar")
            }
        }
    }
}

@Preview
@Composable
private fun ListHuacalesBodyPreview() {
    MaterialTheme {
        val state = ListHuacalesUiState()
        ListHuacalesBody(state) { }
    }
}