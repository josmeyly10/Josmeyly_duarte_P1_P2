package com.example.josmeyly_duarte_p1_p2
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.josmeyly_duarte_p1_p2.Presentation.AppNavigation
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
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            Josmeyly_duarte_P1_P2Theme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("HUACALES", style = MaterialTheme.typography.titleLarge) }
                        )
                    }
                ) { paddingValues ->

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        AppNavigation()
                    }
                }
            }
        }
    }
}
