package com.example.josmeyly_duarte_p1_p2.Presentation.list

import com.example.josmeyly_duarte_p1_p2.domain.model.Huacales

data class ListHuacalesUiState(
    val isLoading: Boolean = false,
    val huacales: List<Huacales> = emptyList(),
    val message: String? = null,
    val navigationToCreate: Boolean = false,
    val navigateToEditId: Int? = null
)