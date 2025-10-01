package com.example.josmeyly_duarte_p1_p2.domain.usecase

import com.example.josmeyly_duarte_p1_p2.domain.model.Huacales
import com.example.josmeyly_duarte_p1_p2.domain.repository.HuacalesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveHuacalesUseCase @Inject constructor(
    private val repository: HuacalesRepository
) {
    operator fun invoke(): Flow<List<Huacales>> = repository.observeHuacales()
}