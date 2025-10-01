package com.example.josmeyly_duarte_p1_p2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.josmeyly_duarte_p1_p2.Presentation.edit.EditHuacalesScreen
import com.example.josmeyly_duarte_p1_p2.Presentation.edit.EditHuacalesUiEvent
import com.example.josmeyly_duarte_p1_p2.Presentation.edit.EditHuacalesViewModel
import com.example.josmeyly_duarte_p1_p2.Presentation.list.ListHuacalesScreen
import com.example.josmeyly_duarte_p1_p2.Presentation.list.ListHuacalesViewModel
import com.example.josmeyly_duarte_p1_p2.ui.theme.Josmeyly_duarte_P1_P2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Josmeyly_duarte_P1_P2Theme {
                val editViewModel: EditHuacalesViewModel = hiltViewModel()
                val listViewModel: ListHuacalesViewModel = hiltViewModel()
                val listState by listViewModel.state.collectAsStateWithLifecycle()

                LaunchedEffect(listState.navigateToEditId) {
                    listState.navigateToEditId?.let { id ->
                        editViewModel.onEvent(EditHuacalesUiEvent.Load(id))
                        listViewModel.onNavigationHandled()
                    }
                }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "HUACALES",
                                    style = MaterialTheme.typography.titleLarge
                                )
                            }
                        )
                    }
                ) { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        EditHuacalesScreen(viewModel = editViewModel)
                        ListHuacalesScreen(viewModel = listViewModel)
                    }
                }
            }
        }
    }
}