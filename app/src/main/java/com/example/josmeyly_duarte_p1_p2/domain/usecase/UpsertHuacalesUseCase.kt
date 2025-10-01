package com.example.josmeyly_duarte_p1_p2.domain.usecase

import com.example.josmeyly_duarte_p1_p2.domain.model.Huacales
import com.example.josmeyly_duarte_p1_p2.domain.repository.HuacalesRepository
import javax.inject.Inject

class UpsertHuacalesUseCase @Inject constructor(
    private val repository: HuacalesRepository
) {
    suspend operator fun invoke(huacales: Huacales): Result<Int> {
        val nombreclienteResult = validateNombreCliente(Huacales.NombreCliente)
        if (!nombreclienteResult.isValid) {
            return Result.failure(IllegalArgumentException(nombreclienteResult.error))
        }
        val cantidadResult = validateCantidad(huacales.Cantidad.toString())
        if (!cantidadResult.isValid) {
            return Result.failure(IllegalArgumentException(cantidadResult.error))
        }

        return runCatching { repository.upsert(huacales) }
    }
}