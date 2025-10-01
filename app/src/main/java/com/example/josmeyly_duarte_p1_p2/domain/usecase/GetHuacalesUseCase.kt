package com.example.josmeyly_duarte_p1_p2.domain.usecase

import com.example.josmeyly_duarte_p1_p2.domain.model.Huacales
import com.example.josmeyly_duarte_p1_p2.domain.repository.HuacalesRepository
import javax.inject.Inject

class GetHuacalesUseCase @Inject constructor(
    private val repository: HuacalesRepository
) {
    suspend operator fun invoke(id: Int?): Huacales? =  repository.getHuacales(id)
}